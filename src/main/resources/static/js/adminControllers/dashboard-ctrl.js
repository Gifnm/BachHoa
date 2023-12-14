const ctx = document.getElementById('revenueChart');
const ctx2 = document.getElementById('myChart2');
const pieChart = document.getElementById('pieChart');
const host = "/bachhoa/api";

const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http) {
	$scope.menu = 'thongke'
	$scope.startDateSelected = new Date('2021-01-01')
	$scope.endDateSelected = new Date()
	$scope.startDate = formatDate($scope.startDateSelected)
	$scope.endDate = formatDate($scope.endDateSelected)
	$scope.mileStone = 'month'
	/* Lợi nhuận */
	$scope.totalProfit = 0;
	/* Doanh thu */
	$scope.revenueData = {
		increaseRevenue: [],
		revenue: [],
		labels: [],
	};
	/* Chi phí */
	$scope.costData = {
		increaseCost: [],
		cost: [], labels: [],
	}

	$scope.totalRevenue = $scope.revenueData.increaseRevenue[$scope.revenueData.increaseRevenue.length - 1]
	$scope.totalCost = $scope.costData.increaseCost[$scope.costData.increaseCost.length - 1]

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
		let year = date.getFullYear();
		let month = ('0' + (date.getMonth() + 1)).slice(-2);
		let day = ('0' + date.getDate()).slice(-2);
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
				borderWidth: 1,
				hidden: true
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
				},
				x: {
					beginAtZero: true,
					title: {
						display: true,
						text: 'Thời gian',
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
				'mile-stone': $scope.mileStone,
				'store-id': $scope.account.store.storeID
			}
		}).then(resp => {
			$scope.revenueData.increaseRevenue = resp.data;

			$http.get(`${host}/statistic/revenue`, {
				params: {
					'start-date': $scope.startDate,
					'end-date': $scope.endDate,
					'mile-stone': $scope.mileStone,
					'store-id': $scope.account.store.storeID
				}
			}).then(resp => {
				$scope.revenueData.revenue = resp.data;

				$http.get(`${host}/statistic/revenue/mile-stone-type`, {
					params: {
						'start-date': $scope.startDate,
						'end-date': $scope.endDate,
						'mile-stone': $scope.mileStone,
						'store-id': $scope.account.store.storeID
					}
				}).then(resp => {
					$scope.revenueData.labels = resp.data;
					revenueChart.data.labels = $scope.revenueData.labels
					revenueChart.data.datasets[0].data = $scope.revenueData.increaseRevenue
					revenueChart.data.datasets[1].data = $scope.revenueData.revenue
					revenueChart.update();
					$scope.totalRevenue = $scope.revenueData.increaseRevenue[$scope.revenueData.increaseRevenue.length - 1]
					$scope.totalBills();
					console.log($scope.billsAmount)

					$http.get(`${host}/statistic/increase-cost`, {
						params: {
							'start-date': $scope.startDate,
							'end-date': $scope.endDate,
							'mile-stone': $scope.mileStone,
							'store-id': $scope.account.store.storeID
						}
					}).then(resp => {
						$scope.costData.increaseCost = resp.data;
						$scope.totalCost = $scope.costData.increaseCost[$scope.costData.increaseCost.length - 1]
						// Tính lợi nhuận
						$scope.totalProfit = $scope.totalRevenue - $scope.totalCost;
						console.log($scope.totalCost)
						$http.get(`${host}/statistic/cost`, {
							params: {
								'start-date': $scope.startDate,
								'end-date': $scope.endDate,
								'mile-stone': $scope.mileStone,
								'store-id': $scope.account.store.storeID
							}
						}).then(resp => {
							$scope.costData.cost = resp.data;

							$http.get(`${host}/statistic/revenue/mile-stone-type`, {
								params: {
									'start-date': $scope.startDate,
									'end-date': $scope.endDate,
									'mile-stone': $scope.mileStone,
									'store-id': $scope.account.store.storeID
								}
							}).then(resp => {
								$scope.costData.labels = resp.data;
								// $scope.totalCost = $scope.costData.increaseCost[$scope.costData.increaseCost.length - 1]
								// // Tính lợi nhuận
								// $scope.totalProfit = $scope.totalRevenue - $scope.totalCost;
								// console.log($scope.totalCost)
							});
						})
					});
				});
			})
		});
	}


	$scope.totalBills = function () {
		let listBill = [];
		$http.get(`${host}/bills`, {
			params: {
				'from-date': $scope.startDate,
				'to-date': $scope.endDate,
				'store-id': $scope.account.store.storeID
			}
		}).then(resp => {
			console.log('Total bills: ' + resp.data.length);
			$scope.billsAmount = resp.data.length;
		});
	}

	$scope.initialize = function () {
		$scope.getAccount().then(() => {
			$scope.showRequest();
			$scope.initChart();
			$scope.allBills = $scope.totalBills();
		});
	}

	$scope.showRequest = function () {
		$http.get(`/bachhoa/api/employee/Request/${$scope.account.store.storeID}`).then(resp => {
			$scope.emRequest = resp.data;
			$scope.badge = $scope.emRequest.length;

		})
	};
	$scope.Denied = function (id) {
		$http.put(`/bachhoa/api/employeeDel/${id}`).then(resp => {
			alert("Thao tác đã hoàn thành!!!!");
			$scope.showRequest();
		})

	}

	$scope.acceptNV = function (id) {
		$http.put(`/bachhoa/api/employeeAccept/${id}`).then(resp => {
			alert("Nhân viên đã được chấp nhận");
			$scope.initialize();
		})
	}

	$scope.initialize();
});