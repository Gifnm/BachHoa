const app_product = angular.module("app", ["ui.bootstrap", "ui.tab.scroll"]);

app_product.controller("product-ctrl", function ($scope, $http) {

    $scope.product = {};
    $scope.discount = {};
    $scope.discountName = "Không";
    $scope.employee = {};

    // gợi ý sản phẩm
    let initAutoComplete = function () {
        $http.get(`/product/getProductName?storeID=${$scope.employee.store.storeID}`).then(resp => {
            $scope.listProductName = resp.data;
        }).catch(error => {
            console.log('Error', error)
        });
    }
    //------------------------------------------------//
    //Tìm sản phẩm
    $scope.find = function (value) {
        $http.get(`/product/findByIDOrName/${value}`).then(resp => {
            $scope.product = resp.data;
            //$scope.product.nearestExpDate = dateFormat($scope.product.nearestExpDate);
            $http.get(`/discount/findByProductIDAndStoreID/${$scope.product.productID}/${$scope.employee.store.storeID}`).then(resp => {
                $scope.discount = resp.data;
                if ($scope.discount.disID == "S25") {
                    $scope.discountName = "Giảm giá 25%";
                } else if ($scope.discount.disID == "S25") {
                    $scope.discountName = "Giảm giá 50%";
                } else {
                    $scope.discountName = "Không";
                }
            }).catch(error => {
                console.log('Error', error)
            });
        }).catch(error => {
            console.log('Error', error)
        });

    }

    let dateFormat = function (value) {
        date = new Date(value);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months start at 0!
        const year = date.getFullYear();
        return formattedDate = `${day}/${month}/${year}`;
    }

    //---------------------------------//
    // cập nhật hồ sơ và kết ca

    //cập nhật hồ sơ
    $scope.admin = false;
    $scope.dataEmployee = {};
    // Kết ca
    $scope.totalMoneyYouPay = 0;
    $scope.formMoney = {};
    $scope.TotalMoneytoPay = 0;

    // Active tab

    $scope.setActive = function () {
        // alert('window.location')
    }

    // Cập nhật thông tin nhân viên
    $scope.updateEmployee = function () {
        const AGE = document.getElementById("age").value;

        const ThisYear = new Date();
        const EmployeeBorn = new Date(AGE);
        const Timelines = ThisYear - EmployeeBorn;
        if ($scope.dataEmployee.email == null || $scope.form.email == "") {
            toastMixin.fire({
                title: "Vui lòng nhập email của bạn, hãy kiểm tra lại !",
                icon: "warning",
            });
            return;
        } else if (AGE == null || AGE == "") {
            toastMixin.fire({
                title: "Vui lòng nhập độ tuổi hiện tại của bạn, hãy xem lại !",
                icon: "warning",
            });
            return;
        } else if (Timelines <= 568036800000) {
            toastMixin.fire({
                title: "Bạn chưa đủ độ tuổi làm việc (18 tuổi), hãy xem lại !",
                icon: "warning",
            });
            return;
        } else if ($scope.dataEmployee.address == null || $scope.dataEmployee.address == "") {
            toastMixin.fire({
                title: "Hãy nhập địa chỉ thường trú của bạn !",
                icon: "warning",
            });
            return;
        }
        const formData = new FormData();
        const fileField = document.querySelector('input[id="uploadImage"]');
        formData.append('file', fileField.files[0]);
        let data = angular.copy($scope.employee);
        data.email = $scope.dataEmployee.email;
        data.age = $scope.dataEmployee.age;
        data.address = $scope.dataEmployee.address;
        if (fileField.files.length != 0) {
            data.pictureURL = "http://172.16.109.217:8081/bachhoaimg/" + fileField.files[0].name;
            $http.post(`/bachhoa/api/employee/updatePhoto`, formData, { transformRequest: angular.identity, headers: { 'Content-Type': undefined } }).then(resp => {
                $http.post(`/bachhoa/api/employee/updateInformation`, data).then(resp => {
                    document.getElementById('img-preview').src = data.pictureURL;
                    toastMixin.fire({
                        title: "Cập nhật thông tin thành công !",
                        icon: "success",
                    });
                }).catch(error => {
                    console.log(error);
                })
            }).catch(error => {
                console.log(error);
            })
        } else {
            $http.post(`/bachhoa/api/employee/updateInformation`, data).then(resp => {
                console.log(resp.data);
                $scope.employee = resp.data;
                toastMixin.fire({
                    title: "Cập nhật thông tin thành công !",
                    icon: "success",
                });
            }).catch(error => {
                console.log(error);
            })
        }

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

    // --------- Kết thúc ca làm ----------- //

    let startDateFormat = function (value) {
        let date = new Date(value);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months start at 0!
        const year = date.getFullYear();
        return `${year}-${month}-${day} ${0}:${0}:${1}`;
    }

    let endDateFormat = function (value) {
        let date = new Date(value);
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

        console.log(moneyInput);
        $scope.totalMoneyYouPay = moneyInput;
        console.log($scope.totalMoneyYouPay);
    };

    //Tính tổng tiền phải thu

    $scope.tinhTongTienThu = function () {
        let employeeID = $scope.employee.employeeID;
        let time = new Date();
        let startDate = startDateFormat(time);
        let endDate = endDateFormat(time);
        $scope.formMoney = {
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
        $http.get(`/bachhoa/api/bill/findByEmployeeAndDate/${employeeID}/${startDate}/${endDate}`).then((resp) => {
            let listbill = resp.data;
            $scope.TotalMoneytoPay = 0;
            angular.forEach(listbill, function (item) {
                let amountReceivable = Math.round((item.totalAmount - item.reduced) / 1000) * 1000;
                $scope.TotalMoneytoPay += amountReceivable;
            });
        }).catch((error) => {
            alert("Lỗi tính tổng tiền!");
            console.log("Error", error);
        });
    }

    // Nút nộp tiền - Bấm là gửi trạng thái qua cho admin duyệt
    $scope.sendMoney = function () {
        if ($scope.TotalMoneytoPay < $scope.totalMoneyYouPay) {
            toastMixin.fire({
                title: "Số tiền bạn nộp phải bằng hoặc nhỏ hơn số tiền bạn phải nộp hôm nay!",
                icon: "warning",
            });
            return;
        }
        if (!confirm('Bạn xác nhận nộp: ' + $scope.totalMoneyYouPay + ' VND')) return;
        // Tính tổng tiền phải nộp (tổng thu các bill trong ngày)
        // Lịch sử nộp
        let createPH = `/bachhoa/api/paymentHistory/create`;
        // Chi tiết số tiền nộp
        let createPD = `/bachhoa/api/paymentDetail/create`;

        let data = {
            employee: $scope.employee,
            admin: null,
            timePay: new Date().getTime(),
            timeReceived: null,
            totalAmount: parseFloat($scope.TotalMoneytoPay),
            totalReceived: parseFloat($scope.totalMoneyYouPay),
            // 0 - Chưa duyệt, thu chưa đủ 1 và đã thu 2
            paied: 0,
        };
        // create payment history
        $http.post(createPH, data).then((resp) => {
            // create payment detail
            let paymentDetail = angular.copy($scope.formMoney);
            paymentDetail.paymentHistory = resp.data;
            console.log(paymentDetail)
            $http.post(createPD, paymentDetail).then(() => {
                toastMixin.fire({
                    title: "Nộp tiền thành công, hãy đợi quản lý duyệt nhé!",
                    icon: "success",
                });
                window.location = "/logout";
            }).catch((error) => {
                alert("Lỗi thêm chi tiết!");
                console.log("Error", error);
            });
        });
    };

    //-----------------------------------------------//

    //-----------------------------------------------//
    //  Tìm Nhân viên

    $scope.findEmployee = function (email) {
        $http.get(`/bachhoa/api/employee/findByEmail/${email}`).then(resp => {
            $scope.employee = resp.data;
            $scope.dataEmployee.email = $scope.employee.email;
            $scope.dataEmployee.address = $scope.employee.address;
            $scope.dataEmployee.age = $scope.employee.age;
            angular.forEach($scope.employee.roles, function (item) {
                if (item.roleID == "qlch") {
                    $scope.admin = true;
                }
            })
            // Khởi chạy lấy mảng gợi ý sản phẩm
            initAutoComplete()
        });
    }


    //------------------------------------------------//
    // Tìm thông tin nhân viên
    let email = document.getElementById('email').innerText;
    //alert(email);
    $scope.findEmployee(email);

    //------------------------------------------------//



})