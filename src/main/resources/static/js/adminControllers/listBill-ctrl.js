const app = angular.module("app", []);
app.controller("billsHistory-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.form = {};
    $scope.billDetails = [];
    $scope.bill = {};
    $scope.totalAmountOfAllBills = 0;
    $scope.account = {};
    $scope.discountDetail = {};
    $scope.listBillID = [];
    // Kết ca
    $scope.totalMoneyYouPay = 0;
    $scope.form = {};
    $scope.TotalMoneytoPay = 0;
    $scope.account = {} //Account of login employee
    let email;

    $scope.getAccount = function () {
        email = document.getElementById('accountEmail').innerText;
        return $http.get(`/bachhoa/api/employee/findByEmail/${email}`).then(resp => {
            $scope.account = resp.data;
            initAutoComplete();
        }).catch(error => {
            console.log("[Product-image-ctrl:getAccount():25]\n> Error: " + error);
        });
    }

    // SweetAlert 2
    var toastMixin = Swal.mixin({
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

    $scope.initialize = function () {
        // load hóa đơn
        $http.get(`/bachhoa/api/bill/all/${$scope.account.store.storeID}`).then(resp => {
            $scope.bills = resp.data;
            $scope.items = [];
            angular.forEach($scope.bills, function (item) {
                item.timeCreate = dateFormat(item.timeCreate);
                let data = {
                    bill: item,
                    amountReceivable: Math.round((item.totalAmount - item.reduced) / 1000) * 1000
                }
                $scope.items.push(data);

            })
            if (resp.data.length == 0) {
                $scope.isNull = true;
            } else {
                $scope.isNull = false;
            }
            Calc();
        });

    }

    // Hiển thị lên form
    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
    }

    //Cập nhật sản phẩm
    $scope.update = function (productID, quantity) {
        let item = $scope.billDetails.find(item => item.productID == productID);
        let index = $scope.billDetails.findIndex(item => item.productID == productID);
        item.quantity = quantity;
        item.totalAmount = (item.product.price + item.product.price * (item.product.vat / 100)) * item.quantity
        $scope.billDetails.splice(index, 1, item);
        $http.put(`/bachhoa/api/billDetail/update`, item).then(() => {
            //alert("Update thành công!");
            $http.get(`/bachhoa/api/bill/findBill/${item.billID}`).then(resp => {
                $scope.bill = resp.data;
                $scope.bill.timeCreate = new Date().getTime();
                $scope.bill.totalAmount = 0;
                angular.forEach($scope.billDetails, function (item) {
                    $scope.bill.totalAmount += item.totalAmount;
                })
                $scope.bill.amountReceivable = Math.round(($scope.bill.totalAmount - $scope.bill.reduced) / 1000) * 1000;
                $http.put(`/bachhoa/api/bill/update`, $scope.bill).then(() => {
                    $scope.findByDate($scope.fromDate, $scope.toDate, 0);
                    console.log($scope.bill);
                    $scope.bill.timeCreate = dateFormat($scope.bill.timeCreate);
                }).catch(error => {
                    alert("Lỗi update hóa đơn!");
                    console.log("Error", error);
                });

            }).catch(error => {
                console.log('Error', error)
            });
        }).catch(error => {
            alert("Lỗi update hóa đơn!");
            console.log("Error", error);
        });




    }

    // Xóa hóa đơn
    $scope.delete = function (billID) {
        if (!confirm('Bạn sẽ xóa hóa đơn này?')) return;
        $http.delete(`/bachhoa/api/bill/delete/${billID}`).then(() => {
            toastMixin.fire({
                title: "Đã xóa hóa đơn!",
                icon: "success",
            });
            $scope.findByDate($scope.fromDate, $scope.toDate, 0);
        }).catch(error => {
            //alert("Hiện không thể xóa hóa đơn!");
            console.log("Error", error);
        });
    }

    // Xóa sản phẩm trong billDetail
    $scope.deleteItem = function (billID, productID) {
        if (!confirm('Bạn sẽ xóa sản phẩm này?')) return;
        $http.delete(`/bachhoa/api/billDetail/delete/${billID}/${productID}`).then(() => {
            console.log("Xóa thành công");
            let index = $scope.billDetails.findIndex(item => item.productID == productID);
            $scope.billDetails.splice(index, 1);
        }).catch(error => {
            console.log("Có lỗi xảy ra", error);
        });
    };

    // Hiển chi tiết
    $scope.showDetail = function (billID) {
        $http.get(`/bachhoa/api/bill/findBill/${billID}`).then(resp => {
            $scope.bill = resp.data;
            $scope.bill.timeCreate = dateFormat($scope.bill.timeCreate);
            $scope.bill.amountReceivable = Math.round(($scope.bill.totalAmount - $scope.bill.reduced) / 1000) * 1000;
        }
        )
        $http.get(`/bachhoa/api/billDetail/findByBillID/${billID}`).then(resp => {
            $scope.billDetails = resp.data;
            // angular.forEach($scope.billDetails, function (item) {
            //     $http.get(`/bachhoa/api/discount/findDiscountIsActive/${item.product.productID}/${$scope.account.store.storeID}`).then(resp => {
            //         $scope.discountDetail = resp.data;
            //         if ($scope.discountDetail.disID === "S25") {
            //             item.discountName = "25%";
            //         } else if ($scope.discountDetail.disID === "S50") {
            //             item.discountName = "50%";
            //         } else {
            //             item.discountName = "Không";
            //         }
            //     })
            // })

        }
        )
    }

    /* Tính tổng thu */
    function Calc() {
        $scope.totalAmountOfAllBills = 0;
        angular.forEach($scope.items, function (item) {
            $scope.totalAmountOfAllBills += item.amountReceivable;
        })
    }

    //Tìm hóa đơn
    $scope.find = function (billID) {
        if (billID == null || billID == undefined || billID == '') {
            $scope.initialize();
        } else {
            let item = $scope.listBillID.find(item => item == billID);
            if (item) {
                $scope.isNull = false;
                $scope.items = [];
                $http.get(`/bachhoa/api/bill/findBill/${billID}?store-id=${$scope.account.store.storeID}`).then(resp => {
                    let bill = resp.data;
                    bill.timeCreate = dateFormat(bill.timeCreate);
                    let data = {
                        bill: bill,
                        amountReceivable: Math.round((bill.totalAmount - bill.reduced) / 1000) * 1000
                    }
                    $scope.items.push(data);
                    Calc();
                }).catch(error => {
                    console.log('Error', error)
                });
            } else {
                $scope.items = [];
                $scope.isNull = true;
            }
        }
    }

    // Set thời gian mặc định
    $scope.SetDefaultDate = function () {
        let date = new Date();
        $scope.fromDate = date;
        $scope.toDate = date;
    }

    // tìm kiếm theo ngày
    $scope.index = 1;
    $scope.maxPage = 0;
    $scope.findByDate = function (fromDate, toDate, index) {
        if (fromDate > toDate) {
            toastMixin.fire({
                title: "Ngày bắt đầu phải nhỏ hơn ngày kết thúc !!",
                icon: "warning",
            });
            // let date = new Date();
            // $scope.fromDate = date;
            // $scope.toDate = date;
            return;
        }
        let fromD = startDateFormat(fromDate);
        let toD = endDateFormat(toDate);
        $http.get(`/bachhoa/api/bill/searchBetween/${fromD}/${toD}?index=${index}&store-id=${$scope.account.store.storeID}`).then(resp => {
            if (resp.data.content.length == 0) {
                $scope.isNull = true;
            } else {
                $scope.isNull = false;
            }
            $scope.items = [];
            $scope.bills = resp.data.content;
            angular.forEach($scope.bills, function (item) {
                item.timeCreate = dateFormat(item.timeCreate);
                let data = {
                    bill: item,
                    amountReceivable: Math.round((item.totalAmount - item.reduced) / 1000) * 1000
                }
                $scope.items.push(data);

            })
            //console.log($scope.items);
            $scope.totalBill = resp.data.totalElements;
            //alert($scope.totalBill);
            if ($scope.totalBill >= 0 && $scope.totalBill <= 8) {
                $scope.maxPage = 1;
            } else {
                $scope.maxPage = Math.ceil($scope.totalBill / 8);
            }
            console.log($scope.maxPage)
            $scope.index = index;
            $scope.pages = [];
            for (let i = 1; i <= $scope.maxPage; i++) {
                $scope.pages.push(i);
            }
            Calc();
        }).catch(error => {
            console.log('Error', error)
        });
    }

    let dateFormat = function (value) {
        date = new Date(value);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months start at 0!
        const year = date.getFullYear();
        var hours = String(date.getHours()).padStart(2, '0');
        var minutes = String(date.getMinutes()).padStart(2, '0');
        var seconds = String(date.getSeconds()).padStart(2, '0');

        return `${day}-${month}-${year} ${hours}:${minutes}:${seconds}`;
    }

    let startDateFormat = function (value) {
        date = new Date(value);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months start at 0!
        const year = date.getFullYear();
        return `${year}-${month}-${day} ${0}:${0}:${1}`;
    }

    let endDateFormat = function (value) {
        date = new Date(value);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months start at 0!
        const year = date.getFullYear();
        return `${year}-${month}-${day} ${23}:${59}:${59}`;
    }

    // gợi ý mã hóa đơn
    let initAutoComplete = function () {
        $http.get(`/bachhoa/api/bill/getBillID/${$scope.account.store.storeID}`).then(resp => {
            $scope.listBillID = resp.data;
        }).catch(error => {
            console.log('Error', error)
        });
    }

    //-----------------------------------------------//

    $scope.refresh = function () {
        $scope.SetDefaultDate();
        document.getElementById("bill_ID").value = "";
        let startDate = startDateFormat(new Date());
        let endDate = endDateFormat(new Date());
        $scope.findByDate(startDate, endDate, 0);
    };

    //-----------------------------------------------//
    // khởi động
    //$scope.initialize();

    $scope.getAccount().then(() => {
        $scope.refresh();
    });

})