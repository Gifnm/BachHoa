const app = angular.module("app", []);
app.controller("paymentHistory-ctrl", function ($scope, $http) {
    $scope.menu = 'thutien'
    $scope.listEmployee = [];
    $scope.paymentDetail = {};
    $scope.paymentHistory = {};
    // $scope.listBill = [];
    $scope.items = [];

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

    // tìm lịch sử nộp tiền theo nhân viên
    $scope.loadHistoryByEmployee = function (employeeID, index) {
        let ID = parseInt(employeeID.substr(0, 1));
        $http.get(`/bachhoa/api/paymentHistory/findByEmployee/${ID}?index=${index}`).then(resp => {
            $scope.items = resp.data.content;
            $scope.isFindByEmployee = true;
            $scope.isFindByDate = false;
            if (resp.data.content.length == 0) {
                $scope.isNull = true;
                $scope.isPagination = true;
            } else {
                $scope.isNull = false;
            }
            angular.forEach($scope.items, function (item) {
                item.timePay = dateFormat(item.timePay);
                if (item.paied == 0) {
                    item.status = "Thu";
                } else if (item.paied == 1) {
                    item.status = "Tiếp tục thu";
                } else {
                    item.status = "Đã thu";
                }

                if (item.timeReceived != null) {
                    item.timeReceived = dateFormat(item.timeReceived);
                }
            });
            $scope.totalReceived = resp.data.totalElements;
            //alert($scope.totalBill);
            if ($scope.totalReceived >= 0 && $scope.totalReceived <= 5) {
                $scope.maxPage = 1;
            } else {
                $scope.maxPage = Math.ceil($scope.totalReceived / 5);
            }
            console.log($scope.maxPage)
            $scope.index = index;
        })
    }

    // hiển thị tất cả các yêu cầu nộp tiền
    let loadPayment = function () {
        //document.getElementById('pagination').setAttribute("style", "display: none;");
        $http.get(`/bachhoa/api/paymentHistory/getPayment?storeID=${$scope.account.store.storeID}`).then(resp => {
            $scope.items = resp.data;
            $scope.isNull = true;
            $scope.isPagination = false;
            angular.forEach($scope.items, function (item) {
                item.timePay = dateFormat(item.timePay);
                if (item.paied == 0) {
                    item.status = "Thu";
                } else if (item.paied == 1) {
                    item.status = "Tiếp tục thu";
                } else {
                    item.status = "Đã thu";
                }

                if (item.timeReceived != null) {
                    item.timeReceived = dateFormat(item.timeReceived);
                }
            })
        });
    }

    // tìm kiếm theo ngày
    $scope.index = 1;
    $scope.maxPage = 0;
    $scope.findByDate = function (fromDate, toDate, index) {
        if (fromDate > toDate) {
            alert('Ngày bắt đầu phải nhỏ hơn ngày kết thúc!');
            let date = new Date();
            $scope.fromDate = date;
            $scope.toDate = date;
            return;
        }
        let fromD = startDateFormat(fromDate);
        let toD = endtDateFormat(toDate);
        $http.get(`/bachhoa/api/paymentHistory/findByDate/${fromD}/${toD}?index=${index}`).then(resp => {
            $scope.items = resp.data.content;
            $scope.isFindByEmployee = false;
            $scope.isFindByDate = true;
            if (resp.data.content.length == 0) {
                $scope.isNull = true;
                $scope.isPagination = true;
            } else {
                $scope.isNull = false;
            }
            angular.forEach($scope.items, function (item) {
                item.timePay = dateFormat(item.timePay);
                if (item.paied == 0) {
                    item.status = "Thu";
                } else if (item.paied == 1) {
                    item.status = "Tiếp tục thu";
                } else {
                    item.status = "Đã thu";
                }

                if (item.timeReceived != null) {
                    item.timeReceived = dateFormat(item.timeReceived);
                }
            })
            //console.log($scope.items);
            $scope.totalReceived = resp.data.totalElements;
            //alert($scope.totalBill);
            if ($scope.totalReceived >= 0 && $scope.totalReceived <= 5) {
                $scope.maxPage = 1;
            } else {
                $scope.maxPage = Math.ceil($scope.totalReceived / 5);
            }
            console.log($scope.maxPage)
            $scope.index = index;
            $scope.pages = [];
            for (let i = 1; i <= $scope.maxPage; i++) {
                $scope.pages.push(i);
            }
        }).catch(error => {
            console.log('Error', error)
        });
    }

    // hiển thị tất cả các lịch sử nộp tiền
    let loadHistory = function () {
        $http.get(`/bachhoa/api/paymentHistory/getAll?index=0&storeID=${$scope.account.store.storeID}`).then(resp => {
            $scope.items = resp.data.content;
            if (resp.data.content.length == 0) {
                $scope.isNull = true;
                $scope.isPagination = true;
            } else {
                $scope.isNull = false;
            }
            angular.forEach($scope.items, function (item) {
                item.timePay = dateFormat(item.timePay);
                if (item.paied == 0) {
                    item.status = "Thu";
                } else if (item.paied == 1) {
                    item.status = "Tiếp tục thu";
                } else {
                    item.status = "Đã thu";
                }

                if (item.timeReceived != null) {
                    item.timeReceived = dateFormat(item.timeReceived);
                }
            })
            //console.log($scope.items);
            $scope.totalReceived = resp.data.totalElements;
            //alert($scope.totalBill);
            if ($scope.totalReceived >= 0 && $scope.totalReceived <= 5) {
                $scope.maxPage = 1;
            } else {
                $scope.maxPage = Math.ceil($scope.totalReceived / 5);
            }
            console.log($scope.maxPage)
            $scope.index = 0;
        });
    }
    // hiển thị chi tiết nộp tiền
    $scope.showPaymentDetail = function (id) {
        $http.get(`/bachhoa/api/paymentDetail/findByPaymentID/${id}`).then(resp => {
            $scope.paymentDetail = resp.data;
            //console.log($scope.paymentDetail);
        }).catch(error => {
            console.log(error);
        })
    }

    let SetDefaultDate = function () {
        let date = new Date();
        $scope.fromDate = date;
        $scope.toDate = date;
    }

    let dateFormat = function (value) {
        date = new Date(value);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months start at 0!
        const year = date.getFullYear();
        var hours = String(date.getHours()).padStart(2, '0');
        var minutes = String(date.getMinutes()).padStart(2, '0');
        var seconds = String(date.getSeconds()).padStart(2, '0');

        return `${day}/${month}/${year} ${hours}:${minutes}:${seconds}`;
    }

    let startDateFormat = function (value) {
        date = new Date(value);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months start at 0!
        const year = date.getFullYear();
        return `${year}-${month}-${day} ${0}:${0}:${1}`;
    }

    let endtDateFormat = function (value) {
        date = new Date(value);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months start at 0!
        const year = date.getFullYear();
        return `${year}-${month}-${day} ${23}:${59}:${59}`;
    }

    // Tính tiền nộp (thay đổi dựa trên giá trị các mệnh giá)
    $scope.updateTotalMoney = function () {
        // Lấy số lượng
        const SL500 = document.getElementById("500").value;
        const SL200 = document.getElementById("200").value;
        const SL100 = document.getElementById("100").value;
        const SL50 = document.getElementById("50").value;
        const SL20 = document.getElementById("20").value;
        const SL10 = document.getElementById("10").value;
        const SL5 = document.getElementById("5").value;
        const SL2 = document.getElementById("2").value;
        const SL1 = document.getElementById("1").value;

        let moneyInput =
            500000 * SL500 +
            200000 * SL200 +
            100000 * SL100 +
            50000 * SL50 +
            20000 * SL20 +
            10000 * SL10 +
            5000 * SL5 +
            2000 * SL2 +
            1000 * SL1;

        //console.log(moneyInput);
        $scope.totalMoneyYouPay = moneyInput;
        //console.log($scope.totalMoneyYouPay);
    };
    // thu tiền
    $scope.pay = function (id) {
        $http.get(`/bachhoa/api/paymentHistory/findByID/${id}`).then(resp => {
            let item = resp.data;
            item.timeReceived = new Date().getTime();
            item.admin = $scope.account;
            if (item.totalReceived < item.totalAmount) {
                item.paied = 1;
            } else {
                item.paied = 2;
            }
            $http.put(`/bachhoa/api/paymentHistory/update`, item).then(() => {
                let index = $scope.items.findIndex(item => item.id == id);
                $scope.items.splice(index, 1, item);
                loadHistory();
                Swal.fire({
                    title: "Đã thu!",
                    icon: "success",
                    confirmButtonColor: "#3085d6",
                    confirmButtonText: "OK"
                })
            })
        }).catch(error => {
            console.log(error);
        });
    };
    // cập nhật thu tiền
    $scope.payUpdate = function (id) {
        let TotalMoneytoPay = $scope.paymentHistory.totalAmount - $scope.paymentHistory.totalReceived;
        if (TotalMoneytoPay < $scope.totalMoneyYouPay) {
            alert('Số tiền bạn nộp phải bằng hoặc nhỏ hơn số tiền bạn phải nộp hôm nay!');
            return;
        }
        if (!confirm('Bạn xác nhận nộp: ' + $scope.totalMoneyYouPay + ' VND')) return;
        $http.get(`/bachhoa/api/paymentHistory/findByID/${id}`).then(resp => {
            let item = resp.data;
            item.timeReceived = new Date().getTime();
            item.admin = $scope.account;
            item.totalReceived = item.totalAmount;
            item.paied = 2;
            let paymentDetail = angular.copy($scope.form);
            item.vnd500 = item.vnd500 + paymentDetail.vnd500;
            item.vnd200 = item.vnd200 + paymentDetail.vnd200;
            item.vnd100 = item.vnd100 + paymentDetail.vnd100;
            item.vnd50 = item.vnd50 + paymentDetail.vnd50;
            item.vnd20 = item.vnd20 + paymentDetail.vnd20;
            item.vnd10 = item.vnd10 + paymentDetail.vnd10;
            item.vnd5 = item.vnd5 + paymentDetail.vnd5;
            item.vnd2 = item.vnd2 + paymentDetail.vnd2;
            item.vnd1 = item.vnd1 + paymentDetail.vnd1;
            $http.put(`/bachhoa/api/paymentHistory/update`, item).then(() => {
                let index = $scope.items.findIndex(item => item.id == id);
                $scope.items.splice(index, 1, item);
                loadHistory();
                Swal.fire({
                    title: "Đã thu!",
                    icon: "success",
                    confirmButtonColor: "#3085d6",
                    confirmButtonText: "OK"
                })
                document.getElementById("pay").setAttribute("data-bs-dismiss", "modal");
                document.getElementById("pay").setAttribute("aria-label", "Close");
                document.getElementById("pay").click();
            })
        }).catch(error => {
            console.log(error);
        });
    };

    // hiển thị modal tiếp tục thu tiền
    $scope.payContinue = function (id) {
        $http.get(`/bachhoa/api/paymentHistory/findByID/${id}`).then(resp => {
            $scope.paymentHistory = resp.data;
            $scope.form = {
                vnd500: 0,
                vnd200: 0,
                vnd100: 0,
                vnd50: 0,
                vnd20: 0,
                vnd10: 0,
                vnd5: 0,
                vnd2: 0,
                vnd1: 0
            }
        }).catch(error => {
            console.log(error);
        })
    }

    $scope.findEmployee = function (email) {
        $http.get(`/bachhoa/api/employee/findByEmail/${email}`).then(resp => {
            $scope.account = resp.data;
            //giợ ý nhân viên
            initAutoComplete();
            // lấy danh sách lịch sử nộp tiền
            loadPayment();
        });
    }

    //------------------------------------------------//
    // lấy danh sách nhân viên
    let initAutoComplete = function () {
        $http.get(`/bachhoa/api/employee/${$scope.account.store.storeID}`).then(resp => {
            let list = resp.data;
            $scope.listEmployee = [];
            angular.forEach(list, function (item) {
                let employee = item.employeeID + " - " + item.employeeName;
                $scope.listEmployee.push(employee);
            })
            console.log($scope.listEmployee);

        }).catch(error => {
            console.log('Error', error)
        });
    }


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
    //--------------------------------------------//

    // Tìm nhân viên theo email
    let email = document.getElementById('accountEmail').innerText;
    $scope.findEmployee(email)

    // chọn mặc định ngày hôm nay
    SetDefaultDate();

})