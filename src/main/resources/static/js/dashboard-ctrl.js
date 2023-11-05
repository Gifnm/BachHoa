const ctx = document.getElementById('myChart');
const ctx2 = document.getElementById('myChart2');
const pieChart = document.getElementById('pieChart');
const host = "http://localhost:8081/bachhoa/api";

const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http) {
    // Chart JS
    new Chart(ctx, {
        type: 'line',
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
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)'
                ],
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

    $scope.initialize = function () {
        $scope.getAccount();
    }

    $scope.initialize();
});