const api_host = "/bachhoa/api";
const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http) {
    $scope.dev_img_host_url = "http://localhost:8081/bachhoaimg/";
    $scope.product_host_url = "http://192.168.1.5:8083/bachhoaimg/";
    $scope.menu = 'sanpham'
    //Variables
    $scope.today = new Date();
    $scope.feature = 'create'
    // const formImageElement = document.getElementById('blah'); //Element image to preview image when create or update product
    $scope.items = [] //List products to manage
    $scope.form = {} //Form data
    let formData = new FormData();
    $scope.DEFAULT_PRODUCT_IMAGE = '/images/image_upload_icon.png';
    $scope.categories = [] //List categories to create new product in form
    $scope.account = {} //Account of login employee
    let storeId;

    let toastMixin = Swal.mixin({
        toast: true,
        icon: "success",
        title: "General Title",
        animation: false,
        position: "top-right",
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        customClass: {
            confirmButton: "btn btn-outline-warning rounded-5 cursor",
            cancelButton: "btn btn-outline-danger rounded-5 cursor",
        },
        buttonsStyling: false,
        didOpen: (toast) => {
            toast.addEventListener("mouseenter", Swal.stopTimer);
            toast.addEventListener("mouseleave", Swal.resumeTimer);
        },
    });

    $scope.getAccount = function () {
        let email = document.getElementById('accountEmail').innerText;
        return $http.get(`${api_host}/employee/findByEmail/${email}`).then(resp => {
            $scope.account = resp.data;
            console.log("[ProductCtrl:getAccount():21]\n> Account: " + $scope.account);
        }).catch(error => {
            alert("[ProductCtrl:initialize():19]\n> Loi lay account");
            console.log("[ProductCtrl:getAccount():24]\n> Error: " + error);
        });
    }
    // Init data on table and form
    $scope.initialize = function () {
        $scope.getAccount().then(() => {
            $scope.showRequest();
            // Load products into table
            storeId = $scope.account.store.storeID;
            console.log("[ProductCtrl:initialize():28]\n> Store Id: " + storeId + " account name: " + $scope.account.employeeName);
            let url = `${api_host}/products/${storeId}`;
            $http.get(url).then(resp => {
                $scope.items = resp.data;
                $scope.items.forEach(item => {
                    item.nearestExpDate = new Date(item.nearestExpDate)
                })
            });
            // Load categories onto form's comboboxes
            url = `${api_host}/${storeId}/categories`;
            $http.get(url).then(resp => {
                let categories = resp.data;
                categories.forEach(category => {
                    let categoryItem = { category, editing: false };
                    $scope.categories.push(categoryItem);
                });
            });
            // Set default value in form
            $scope.form.store = $scope.account.store;
            $scope.form.image = $scope.DEFAULT_PRODUCT_IMAGE;
            $scope.search("");
        });
    }

    //Save new product
    $scope.create = function () {
        let item = angular.copy($scope.form);
        const fileName = document.getElementById('uploadImage').files[0].name;
        //append the host url default to sync
        item.image = $scope.product_host_url + fileName;
        // Cal api
        let url = `${api_host}/products`;
        $http.post(url, item).then(resp => {
            $scope.reset();
            $scope.initialize();
            let defaultShelve;
            $http.get(`${api_host}/shelve/0`).then(resp => {
                defaultShelve = resp.data;
            })
            let defaultProductPosition = {
                id: 0,
                product: resp.data,
                displayQuantity: 0,
                displayShelves: defaultShelve,
                store: $scope.account.store,
                form: 0
            }
            $http.post(`${api_host}/productPosition/insert`, defaultProductPosition).then(resp => {
                console.log('Create default position successfully: ' + resp.data);
            }).catch((error) => {
                console.log('Create fail: ' + error);
            })
            toastMixin.fire({
                title: "Thêm sản phẩm thành công!",
                icon: "success",
            })
        }).catch(error => {
            console.log("[product-ctrl.js:create():62]\n> Error", error);
            toastMixin.fire({
                title: "Lỗi thêm sản phẩm!",
                icon: "error",
            })
        })
    };


    $scope.update = function () {
        let item = angular.copy($scope.form);
        const fileName = document.getElementById('uploadImage').files[0].name;
        if (fileName != null) {
            //append the host url default to sync
            item.image = $scope.product_host_url + fileName;
            $scope.uploadImage();
        }
        // Cal api
        let url = `${api_host}/products/${item.productID}`;
        $http.put(url, item).then(resp => {
            let index = $scope.items.findIndex(p => p.productID == item.productID);
            $scope.items[index] = item;
            $scope.initialize();
            toastMixin.fire({
                title: 'Cập nhật sản phẩm thành công',
                icon: 'success'
            })
        }).catch(error => {
            toastMixin.fire({
                title: 'Cập nhật sản phẩm thất bại',
                icon: 'error'
            })
            console.log("[product-ctrl.js:update():88]\n> Error", error);
        })
    }

    $scope.delete = function (id) {
        if (confirm("THIS ACTION CAN'T UNDO!\nAre you sure to delete this product?") == true) {
            let url = `${api_host}/products/${id}`;
            $http.delete(url).then(resp => {
                let index = $scope.items.findIndex(item => item.productID == $scope.form.productID);
                $scope.items.splice(index, 1);
                $scope.reset();
                $scope.initialize();
                alert("[product-ctrl.js:delete():100]\n> Delete successfully!");
            }).catch(error => {
                console.log("[product-ctrl.js:delete():102]\n> Error", error)
            });
        }
    }

    $scope.changeStatus = function (product) {
        product.status = !product.status;
        $http.put(`${api_host}/products/update`, product).then(resp => {
            $scope.initialize();
            toastMixin.fire({
                title: "Cập nhật trạng thái thành công!",
                icon: "success",
            });
            console.log('Set inactive successfully! ', resp.data);
        }).catch(error => {
            toastMixin.fire({
                title: "Cập nhật thất bại!",
                icon: "warning",
            });
            console.log("Set status fail\n> Error: ", error);
        })
    }

    //Clear form information
    $scope.reset = function () {
        $scope.feature = 'create';
        $scope.form = {
            nearestExpDate: new Date(),
            image: null,
            status: true,
            store: $scope.account.store
        }
        // formImageElement.src = $scope.DEFAULT_PRODUCT_IMAGE;
    }

    //Show detail product in form
    $scope.edit = function (item) {
        $scope.feature = 'update'
        $scope.form = angular.copy(item);
        // if ($scope.item.image == null) {
        //     formImageElement.src = $scope.DEFAULT_PRODUCT_IMAGE;
        // }
        // formImageElement.src = $scope.form.image;
    }

    $scope.calculateDaysRemaining = function (nearestExpDate) {
        const currentDate = new Date();
        const expDate = new Date(nearestExpDate);
        const timeDiff = expDate.getTime() - currentDate.getTime();
        const daysRemaining = Math.ceil(timeDiff / (1000 * 3600 * 24));
        return daysRemaining;
    }


    //Start: Image
    //Preview image when change (NOT DONE)
    // $scope.imageChanged = function (e, files) {
    //     // Check if the user has selected a file.
    //     if (files) {
    //         //Show image choosing on element with id 'blah'
    //         formImageElement.src = e
    //         //Save name of image file choosing
    //         let fileName = files.name;
    //         $scope.form.image = $scope.product_host_url + fileName;
    //         console.log("[product-ctrl.js:imageChanged():130] - Form image file name: " + $scope.form.image);
    //     } else {
    //         // The user has not selected a file.
    //         alert('[product-ctrl.js:imageChanged():141]\n> Please select an image file.');
    //     }
    // }

    $scope.uploadImage = function () {
        alert("Uploading image...");
        let fileInput = document.getElementById('uploadImage');
        let file = fileInput.files[0];

        if (!file) {
            alert("Please select an image to upload.");
            return;
        }

        let formData = new FormData();
        formData.append('file', file);

        $http.post(api_host + '/image/upload', formData, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            console.log("Upload image successfully!\n> Image path: " + resp.data.path);
            document.getElementById('uploadImage').value = null;
        }).catch(error => {
            console.log("Error: ", error);
        });
    };

    //Get image of product by image name
    $scope.getImage = function (imageName) {
        const lastIndex = imageName.lastIndexOf("/");
        const substring = imageName.substring(lastIndex + 1);
        return $scope.dev_img_host_url + substring;
    }

    const input = document.getElementById('uploadImage');
    const image = document.getElementById('img-preview');

    input.addEventListener('change', (e) => {
        if (e.target.files.length) {
            const src = URL.createObjectURL(e.target.files[0]);
            image.src = src;
            console.log(e.target.files[0].name)
        }

    });
    //End: Image

    //Start: Pagination
    $scope.currentPage = 0;
    $scope.pageSize = 20;
    $scope.reverse = false;

    $scope.numberOfPages = function () {
        return Math.ceil($scope.items.length / $scope.pageSize);
    }
    $scope.range = function (start, end) {
        let ret = [];
        if (!end) {
            end = start;
            start = 0;
        }
        for (let i = start; i < end; i++) {
            ret.push(i);
        }
        return ret;
    };

    // init the filtered items
    $scope.search = function (query) {
        let keyword = encodeURI(query)
        console.log("[product-ctrl.js:search():195]\n> Search keyword: " + keyword)
        let url = `${api_host}/products/search?q=${keyword}&storeid=${storeId}`;
        console.log("[product-ctrl.js:search():197]\n> Search path: " + url)
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.nearestExpDate = new Date(item.nearestExpDate)
            })
        })
        $scope.currentPage = 0;
    };

    $scope.setPage = function (page) {
        $scope.currentPage = page;
    }
    //End: Pagination
    $scope.initialize();

    //Start: CRUD Category
    $scope.selectedOption = '';

    $scope.selectOption = function (option) {
        $scope.form.categories = option.category;
    };

    $scope.editOption = function (option) {
        option.editing = true;
    };

    $scope.saveOption = function (option) {
        let categoryId = option.category.categoriesID;
        let url = `${api_host}/categories/${categoryId}`;
        $http.put(url, option.category).then(resp => {
            option.editing = false;
        }).catch(error => {
            console.log("[product-ctrl.js:saveOption():320]\n> Error", error);
        });
    };

    $scope.deleteOption = function (option) {
        let index = $scope.categories.indexOf(option);
        if (index > -1) {
            let categoryId = option.category.categoriesID;
            let url = `${api_host}/categories/${categoryId}`;
            $http.delete(url).then(resp => {
                $scope.categories.splice(index, 1);
                toastMixin.fire({
                    title: 'Đã xóa loại hàng \"' + option.category.categoriesName + '\" thành công!',
                    icon: 'success'
                })
            }).catch(error => {
                console.log("[product-ctrl.js:deleteOption():326]\n> Error", error);
                toastMixin.fire({
                    title: 'Không thể xóa vì loại hàng này vẫn còn sản phẩm đang kinh doanh!',
                    icon: 'error'
                })
            });
        }
    };

    $scope.addOption = function () {
        if ($scope.newOption) {
            let existingOption = $scope.categories.find(function (option) {
                return option.category.categoriesName === $scope.newOption;
            });
            if (!existingOption) {
                let url = `${api_host}/categories`;
                let item = { categoriesName: $scope.newOption, storeID: $scope.account.store.storeID }
                $http.post(url, item).then(resp => {
                    $scope.categories.push({ category: resp.data, editing: false });
                }).catch(error => {
                    console.log("[product-ctrl.js:addOption():336]\n> Error", error);
                });
            }
            $scope.newOption = '';
        }
    };

    $scope.isOptionExists = function (newOption) {
        return $scope.categories.some(function (option) {
            return option.category.categoriesName === newOption;
        });
    };
    //End: CRUD Category

    //Start: Employee request
    $scope.showRequest = function () {
        $http.get(`/bachhoa/api/employee/Request/${$scope.account.store.storeID}`).then(resp => {
            console.log(`/bachhoa/api/employee/Request/${$scope.account.store.storeID}`)
            $scope.emRequest = resp.data;
            $scope.badge = $scope.emRequest.length;

        })
    };
    $scope.Denied = function (id) {
        $http.put(`/bachhoa/api/employeeDel/${id}`).then(resp => {
            toastMixin.fire({
                title: 'Đã từ chối nhân viên.',
                icon: 'success'
            })
            $scope.showRequest();
        })

    }

    $scope.acceptNV = function (id) {
        $http.put(`/bachhoa/api/employeeAccept/${id}`).then(resp => {
            toastMixin.fire({
                title: 'Nhân viên đã được chấp nhận!',
                icon: 'success'
            })
            $scope.showRequest();
        })

    }
    //End: Employee request
});
// ctrl.$inject = ['$scope', '$filter'];
//We already have a limitTo filter built-in to angular,
//let's make a startFrom filter
app.filter('startFrom', function () {
    return function (input, start) {
        start = +start; //parse to int
        return input.slice(start);
    }
});