const host = "http://localhost:8081/bachhoa/api";

const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http) {
    $scope.menu = 'sanpham'
    //Variables
    $scope.today = new Date();
    $scope.feature = 'create'
    const formImageElement = document.getElementById('blah'); //Element image to preview image when create or update product
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
        return $http.get(`${host}/employee/findByEmail/${email}`).then(resp => {
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
            let url = `${host}/products/${storeId}`;
            $http.get(url).then(resp => {
                $scope.items = resp.data;
                $scope.items.forEach(item => {
                    item.nearestExpDate = new Date(item.nearestExpDate)
                })
            });
            // Load categories onto form's comboboxes
            url = `${host}/categories`;
            $http.get(url).then(resp => {
                $scope.categories = resp.data;
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
        // Cal api
        let url = `${host}/products`;
        $http.post(url, item).then(resp => {
            $scope.reset();
            $scope.initialize();
            let defaultShelve;
            $http.get(`${host}/shelve/0`).then(resp => {
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
            $http.post(`${host}/productPosition/insert`, defaultProductPosition).then(resp => {
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
        //create product with image
        // Collect product info and image in form into formData
        // formData = new FormData();
        // formData.append('product', JSON.stringify(angular.copy($scope.form)));
        // formData.append('image', $scope.image);

        // console.log("[product-ctrl.js:imageChanged():53]\n> Form data: " + $scope.formData);

        // Call api
        // let url = `${host}/upload`;
        // $http
        //     .post(url, formData, {
        //         transformRequest: angular.identity,
        //         headers: { 'Content-type': 'multipart/form-data; boundary=--WebKitFormBoundary7MA4YWxkTrZu0gW' },
        //     })
        //     .then(resp => {
        //         console.log("[product-ctrl.js:create():60]\n> POST SUCCESS");
        //         resp.data.nearestExpDate = new Date(resp.data.nearestExpDate);
        //         $scope.items.push(resp.data);
        //         $scope.reset();
        //         alert("[product-ctrl.js:create():68]\n> Them moi thanh cong!");
        //     })
        //     .catch(error => {
        //         alert("[product-ctrl.js:create():71]\n>Loi them moi san pham!");
        //         console.log("Error: " + error);
        //     });
    };


    $scope.update = function () {

        let item = angular.copy($scope.form);
        // Cal api
        let url = `${host}/products/${item.productID}`;
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
            let url = `${host}/products/${id}`;
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
        $http.put(`${host}/products/update`, product).then(resp => {
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
        formImageElement.src = $scope.DEFAULT_PRODUCT_IMAGE;
    }

    //Show detail product in form
    $scope.edit = function (item) {
        $scope.feature = 'update'
        $scope.form = angular.copy(item);
        if ($scope.item.image == null) {
            formImageElement.src = $scope.DEFAULT_PRODUCT_IMAGE;
        }
        formImageElement.src = $scope.form.image;
    }


    //Preview image when change (NOT DONE)
    $scope.imageChanged = function (e, files) {
        // Check if the user has selected a file.
        if (files) {
            //Show image choosing on element with id 'blah'
            formImageElement.src = e
            //Save name of image file choosing
            let fileName = files.name;
            $scope.form.image = "http://192.168.1.5:8083/bachhoaimg//" + fileName;
            console.log("[product-ctrl.js:imageChanged():130] - Form image file name: " + $scope.form.image);
        } else {
            // The user has not selected a file.
            alert('[product-ctrl.js:imageChanged():141]\n> Please select an image file.');
        }

        // let data = new FormData();
        // data.append('file', files[0]);
        // let url = `${host}/upload/images`;
        // $http.post(url, data, {
        //     transformRequest: angular.identify,
        //     headers: {'Content-Type': undefined}
        // }).then(resp => {
        //     alert(resp.data.path)
        //     $scope.form.image = resp.data.name;
        // }).catch(error => {
        //     alert("Loi upload anh");
        //     console.log("Error: ", error);
        // })
    }

    $scope.currentPage = 0;
    $scope.pageSize = 20;
    // $scope.sortingOrder = sortingOrder;
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
        let url = `${host}/products/search?q=${keyword}&storeid=${storeId}`;
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

    $scope.initialize();
    const input = document.getElementById('uploadImage');
    const image = document.getElementById('img-preview');

    input.addEventListener('change', (e) => {
        if (e.target.files.length) {
            const src = URL.createObjectURL(e.target.files[0]);
            image.src = src;
            console.log(e.target.files[0].name)
        }

    });


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