const app = angular.module("app", []);
app.controller("inventoryHistory-ctrl", function ($scope, $http) {
    $scope.menu = 'kiemkho'
    $scope.items = [];
    // tìm kiếm theo ngày
    $scope.index = 1;
    $scope.maxPage = 0;
    $scope.totalItem = 0;
    $scope.account = {};
    $scope.findByDate = function (date, index) {
        let fromD = startDateFormat(date);
        let toD = endDateFormat(date);
        $http.get(`/bachhoa/api/inventoryHistory/findByDate/${$scope.account.store.storeID}/${fromD}/${toD}?index=${index}`).then(resp => {
            $scope.items = resp.data.content;
            if (resp.data.content.length == 0) {
                $scope.isNull = true;
            } else {
                $scope.isNull = false;
            }
            angular.forEach($scope.items, function (item) {
                item.countingTime = dateFormat(item.countingTime);
            })
            $scope.totalItem = resp.data.totalElements;
            if ($scope.totalItem >= 0 && $scope.totalItem <= 8) {
                $scope.maxPage = 1;
            } else {
                $scope.maxPage = Math.ceil($scope.totalItem / 8);
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

    let SetDefaultDate = function () {
        $scope.date = new Date();
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

    $scope.findEmployee = function (email) {
        $http.get(`/bachhoa/api/employee/findByEmail/${email}`).then(resp => {
            $scope.account = resp.data;
            let date = new Date();
            $scope.findByDate(date, 0);
        });
    }



    //--------------------------------------------//

    // Tìm nhân viên theo email
    let email = document.getElementById('accountEmail').innerText;
    $scope.findEmployee(email)
    // chọn mặc định ngày hôm nay
    SetDefaultDate();
    // lấy danh sách lịch sử kiểm kê ngày hôm nay

})

function exportData() {
    var table2excel = new Table2Excel();
    table2excel.export(document.querySelectorAll("table.table"));
}