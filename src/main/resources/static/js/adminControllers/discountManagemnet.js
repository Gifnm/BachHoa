const app = angular.module("app", []);
app.controller("discountManagement-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.employee = {};
    $scope.listProduct = [];
    $scope.listProductID = [];
    $scope.totalProduct = 0;
    $scope.discount = {};
    $scope.discountList = [{ name: "Giảm giá 25%", value: "S25" }, { name: "Giảm giá 50%", value: "S50" }, { name: "Mua 2 tặng 1", value: "2T1" }];
    $scope.discountDetail = {};
    $scope.form = {};
    $scope.product = {};

    // Lấy danh sách sản phẩm
    let initAutoComplete = function () {
        $http.get(`/bachhoa/api/products/${$scope.employee.store.storeID}`).then(resp => {
            let list = resp.data;
            $scope.listProduct = [];
            angular.forEach(list, function (item) {
                let product = item.productID + " - " + item.productName;
                $scope.listProduct.push(product);
            })
            let date = new Date();
            $scope.fromDate = date;
            $scope.toDate = date;
        }).catch(error => {
            console.log('Error', error)
        });
    }
    //-----------------------------------------------//
    //	Tìm Nhân viên

    $scope.findEmployee = function (email) {
        $http.get(`/bachhoa/api/employee/findByEmail/${email}`).then(resp => {
            $scope.employee = resp.data;
            $scope.loadAll();
            initAutoComplete();
        });
    }

    //-----------------------------------------------//
    $scope.loadAll = function () {
        $http.get(`/bachhoa/api/discount/findByStoreID/${$scope.employee.store.storeID}`).then(resp => {
            $scope.items = resp.data;
            if (resp.data.length == 0) {
                $scope.isNull = true;
            } else {
                $scope.isNull = false;
            }
        }).catch(error => {
            console.log(error);
        })
    }

    //-----------------------------------------------//
    $scope.findByProductID = function (id) {
        if (id == "" || id == null) {
            $scope.loadAll();
        } else {
            let productID = id.substr(0, 13);
            $http.get(`/bachhoa/api/discount/findByStoreIDAndProductID/${$scope.employee.store.storeID}/${productID}`).then(resp => {
                $scope.items = resp.data;
                if (resp.data.length == 0) {
                    $scope.isNull = true;
                } else {
                    $scope.isNull = false;
                }
            }).catch(error => {
                console.log(error);
            })
        }
    }

    $scope.findByDate = function (fromDate, toDate) {
        if (fromDate > toDate) {
            alert("Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc!");
            let date = new Date();
            $scope.fromDate = date;
            $scope.toDate = date;
            return;
        }
        let TD = dateFormat(fromDate);
        let ED = dateFormat(toDate);
        $http.get(`/bachhoa/api/discount/findByDate/${$scope.employee.store.storeID}/${TD}/${ED}`).then(resp => {
            $scope.items = resp.data;
            console.log($scope.items)
            if (resp.data.length == 0) {
                $scope.isNull = true;
            } else {
                $scope.isNull = false;
            }
        }).catch(error => {
            console.log(error);
        })

    }
    // Chỉnh sửa chương trình khuyến mãi

    $scope.update = function (id) {
        let form = angular.copy($scope.discountDetail);
        $http.get(`/bachhoa/api/discount/getDiscount/${form.disID}`).then(resp => {
            let discount = resp.data;
            let index = $scope.items.findIndex(item => item.productID == id);
            let data = $scope.discountDetail;
            data.disID = form.disID;
            data.discount = discount;
            data.startTime = form.startTime;
            data.endTime = form.endTime;
            data.activity = 1;
            $scope.items.splice(index, 1, data);
            console.log(data)
            $http.put(`/bachhoa/api/discount/update/${form.disID}/${dateFormat(form.startTime)}/${dateFormat(form.endTime)}/${id}/${$scope.employee.store.storeID}`).then(() => {
                alert('Chương trinh khuyến mãi đã được cập nhật!');
                document.getElementById("start").setAttribute("data-bs-dismiss", "modal");
                document.getElementById("start").setAttribute("aria-label", "Close");
                document.getElementById("start").click();
            }).catch(error => {
                console.log(error);
            })
        })

    }

    $scope.edit = function (id) {
        $scope.discountDetail = $scope.items.find(item => item.productID == id);
        $scope.discountDetail.startTime = new Date($scope.discountDetail.startTime);
        $scope.discountDetail.endTime = new Date($scope.discountDetail.endTime);
        console.log($scope.discountDetail);
    }

    //Chọn sản phẩm để thêm khuyến mãi
    $scope.addList = function (id) {
        if (id == "" || id == null) {
            alert("Vui lòng chọn sản phẩm");
            return;
        }
        let ID = id.substr(0, 13);
        let item = $scope.listProductID.find(item => item == ID);
        let check = $scope.items.find(item => item.productID == ID);
        if (item) {
            alert("Sản phẩm này đã có trong danh sách");
        } else if (check) {
            alert("Sản phẩm này đã có chương trình khuyến mãi là: " + check.discount.discountType);
        } else {
            $scope.listProductID.unshift(ID);
            $scope.totalProduct = $scope.listProductID.length;
        }
        $scope.id = "";

    }
    //Xóa sản phẩm ra khỏi danh sách khuyến mãi
    $scope.removeItem = function (id) {
        let index = $scope.listProductID.findIndex(item => item.productID == id);
        $scope.listProductID.splice(index, 1);
        $scope.totalProduct = $scope.listProductID.length;
    }

    //Dừng khuyến mãi
    $scope.stop = function (id) {
        let index = $scope.items.findIndex(item => item.productID == id);
        let data = $scope.items.find(item => item.productID == id);
        data.activity = 0;
        $scope.items.splice(index, 1, data);
        $http.put(`/bachhoa/api/discount/update`, data).then(() => {
            alert('Đã kết thúc chương trình khuyến mãi!')
        }).catch(error => {
            console.log(error);
        })
    }

    //Xóa khuyến mãi
    $scope.delete = function (id) {
        let index = $scope.items.findIndex(item => item.productID == id);
        $scope.items.splice(index, 1);
        $http.delete(`/bachhoa/api/discount/delete/${id}/${$scope.employee.store.storeID}`).then(() => {
            alert('Xóa chương trình khuyến mãi thành công!')
            document.getElementById("close").setAttribute("data-bs-dismiss", "modal");
            document.getElementById("close").setAttribute("aria-label", "Close");
            document.getElementById("close").click();
        }).catch(error => {
            console.log(error);
        })
    }

    //Lưu vào db
    $scope.save = function () {
        const form = angular.copy($scope.form);
        if ($scope.listProductID.length == 0) {
            alert('Bạn cần chọn sản phẩm');
            return;
        } else if (form.disID == null) {
            alert('Bạn cần chọn loại hình khuyến mãi!');
            return;
        } else if (form.startDate == null) {
            alert('Bạn cần chọn ngày bắt đầu!');
            return;
        } else if (form.endDate == null) {
            alert('Bạn cần chọn ngày kết thúc!');
            return;
        }
        console.log(form)
        $http.get(`/bachhoa/api/discount/getDiscount/${form.disID}`).then(resp => {
            $scope.discount = resp.data;
            //console.log($scope.discount)
            angular.forEach($scope.listProductID, item => {
                $http.get(`/product/findByID/${item}/${$scope.employee.store.storeID}`).then(resp => {
                    $scope.product = resp.data;
                    //console.log($scope.product)
                    let data = {
                        disID: form.disID,
                        storeID: $scope.employee.store.storeID,
                        productID: item,
                        discount: $scope.discount,
                        store: $scope.employee.store,
                        product: $scope.product,
                        activity: 1,
                        startTime: form.startDate,
                        endTime: form.endDate
                    }
                    //console.log(data);
                    $http.post(`/bachhoa/api/discount/create`, data).then(resp => {
                        console.log(resp.data);
                    }).catch(error => {
                        console.log(error);
                    })
                }).catch(error => {
                    console.log(error);
                })
            })
            alert("Thêm khuyến mãi thành công!")
        }).catch(error => {
            console.log(error);
        })
    }

    let dateFormat = function (value) {
        date = new Date(value);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months start at 0!
        const year = date.getFullYear();
        return `${year}-${month}-${day}`;
    }

    $scope.findEmployee('dongnghiepit@gmail.com');
})