const ctx = document.getElementById('revenueChart');
const ctx2 = document.getElementById('myChart2');
const pieChart = document.getElementById('pieChart');
const host = "http://localhost:8081/bachhoa/api";

const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http, $filter) {
    $scope.startDateSelected = new Date('2021-01-01')
    $scope.endDateSelected = new Date()
    $scope.startDate = formatDate($scope.startDateSelected)
    $scope.endDate = formatDate($scope.endDateSelected)
    $scope.mileStone = 'year'
    $scope.revenueData = {
        increaseRevenue: [],
        revenue: [],
        labels: [],
    };

    $scope.totalRevenue = $scope.revenueData.increaseRevenue[$scope.revenueData.increaseRevenue.length - 1]
    $scope.billsAmount = 0

    $scope.translateTypeMileStone = function () {
        if ($scope.mileStone == 'day') {
            return 'ngày';
        } else if ($scope.mileStone == 'month') {
            return 'tháng';
        } else if ($scope.mileStone == 'year') {
            return 'năm';
        }
    }

    function formatDate(date) {
        var year = date.getFullYear();
        var month = ('0' + (date.getMonth() + 1)).slice(-2);
        var day = ('0' + date.getDate()).slice(-2);
        return year + '-' + month + '-' + day;
    }

    // Chart JS
    const revenueChart = new Chart(ctx, {
        data: {
            labels: $scope.revenueData.labels,
            datasets: [{
                type: 'line',
                label: 'Tổng doanh thu',
                data: $scope.revenueData.increaseRevenue,
                borderWidth: 1
            }, {
                type: 'bar',
                label: 'Doanh thu trong ' + $scope.translateTypeMileStone(),
                data: $scope.revenueData.revenue,
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Doanh thu',
                    },
                }
            }
        }
    });

    new Chart(ctx2, {
        type: 'bar',
        data: {
            labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
            datasets: [{
                label: '# of Votes',
                data: [12, 19, 3, 5, 2, 3],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
    new Chart(pieChart, {
        type: 'pie',
        data: {
            labels: [
                'Tawm',
                'Blue',
                'Yellow'
            ],
            datasets: [{
                label: 'Doanh thu',
                data: [300, 50, 100],
                hoverOffset: 4
            }]
        },
    });

    $scope.account = {} //Account of login employee
    $scope.getAccount = function () {
        // Fake auth account
        let email = document.getElementById('accountEmail').innerText;
        return $http.get(`${host}/employee/findByEmail/${email}`).then(resp => {
            $scope.account = resp.data;
            console.log("[ProductCtrl:getAccount():21]\n> Account: " + $scope.account);
        }).catch(error => {
            alert("[ProductCtrl:initialize():19]\n> Loi lay account");
            console.log("[ProductCtrl:getAccount():24]\n> Error: " + error);
        });
    }

    $scope.initChart = function () {
        $scope.updateDataRevenueChart();
    }

    $scope.updateDataRevenueChart = function () {
        $scope.startDate = formatDate($scope.startDateSelected)
        $scope.endDate = formatDate($scope.endDateSelected)
        console.log($scope.startDate + ' - ' + $scope.endDate + ' - ' + $scope.mileStone);
        $http.get(`${host}/statistic/increase-revenue`, {
            params: {
                'start-date': $scope.startDate,
                'end-date': $scope.endDate,
                'mile-stone': $scope.mileStone
            }
        }).then(resp => {
            $scope.revenueData.increaseRevenue = resp.data;

            $http.get(`${host}/statistic/revenue`, {
                params: {
                    'start-date': $scope.startDate,
                    'end-date': $scope.endDate,
                    'mile-stone': $scope.mileStone
                }
            }).then(resp => {
                $scope.revenueData.revenue = resp.data;

                $http.get(`${host}/statistic/revenue/mile-stone-type`, {
                    params: {
                        'start-date': $scope.startDate,
                        'end-date': $scope.endDate,
                        'mile-stone': $scope.mileStone
                    }
                }).then(resp => {
                    console.log($scope.revenueData.increaseRevenue)
                    console.log($scope.revenueData.revenue)
                    console.log($scope.revenueData.labels)
                    $scope.revenueData.labels = resp.data;
                    revenueChart.data.labels = $scope.revenueData.labels
                    revenueChart.data.datasets[0].data = $scope.revenueData.increaseRevenue
                    revenueChart.data.datasets[1].data = $scope.revenueData.revenue
                    revenueChart.update();
                    $scope.totalRevenue = $scope.revenueData.increaseRevenue[$scope.revenueData.increaseRevenue.length - 1]
                    $scope.totalBills();
                    console.log($scope.billsAmount)
                });
            })
        });
    }

    $scope.totalBills = function () {
        let listBill = [];
        $http.get(`${host}/bills`, {
            params: {
                'from-date': $scope.startDate,
                'to-date': $scope.endDate
            }
        }).then(resp => {
            console.log('Total bills: ' + resp.data.length);
            $scope.billsAmount = resp.data.length;
        });
    }

    $scope.initialize = function () {
        $scope.getAccount();
        $scope.initChart();
        $scope.totalRevenue = $scope.revenueData.increaseRevenue[$scope.revenueData.increaseRevenue.length - 1]
        $scope.allBills = $scope.totalBills();
    }

    $scope.initialize();
});