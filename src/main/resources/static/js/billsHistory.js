const app = angular.module("app", []);
app.controller("billsHistory-ctrl", function ($scope, $http) {
	$scope.items = [];
	$scope.form = {};
	$scope.billDetails = [];
	$scope.bill = {};
	$scope.totalAmountOfAllBills = 0;
	$scope.employee = {};
	$scope.discountDetail = {};
	$scope.listBillID = [];
	// Kết ca
	$scope.totalMoneyYouPay = 0;
	$scope.form = {};
	$scope.TotalMoneytoPay = 0;

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


	$scope.refresh = function () {
		$scope.autocompleteInput.init();
		$scope.SetDefaultDate();
		document.getElementById("bill_ID").value = "";
		let startDate = startDateFormat(new Date());
		let endDate = endDateFormat(new Date());
		$scope.findByDate(startDate, endDate, 0);
	};

	$scope.initialize = function () {
		// load hóa đơn
		$http.get(`/bachhoa/api/bill/all/${$scope.employee.store.storeID}`).then(resp => {
			$scope.bills = resp.data;
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
				$http.put(`/bachhoa/api/bill/update`, $scope.bill).then(() => {
					//alert("Update bill thành công!");
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
			angular.forEach($scope.billDetails, function (item) {
				$http.get(`/bachhoa/api/discount/findDiscountIsActive/${item.product.productID}/${$scope.employee.store.storeID}`).then(resp => {
					$scope.discountDetail = resp.data;
					if ($scope.discountDetail.disID === "S25") {
						item.discountName = "25%";
					} else if ($scope.discountDetail.disID === "S50") {
						item.discountName = "50%";
					} else {
						item.discountName = "Không";
					}
				})
			})

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
				$http.get(`/bachhoa/api/bill/findBill/${billID}`).then(resp => {
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
		$http.get(`/bachhoa/api/bill/searchBetween/${fromD}/${toD}?index=${index}`).then(resp => {
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
		$http.get(`/bachhoa/api/bill/getBillID/${$scope.employee.store.storeID}`).then(resp => {
			$scope.listBillID = resp.data;
		}).catch(error => {
			console.log('Error', error)
		});
	}
	//-----------------------------------------------//
	//	Tìm Nhân viên

	$scope.findEmployee = function (email) {
		$http.get(`/bachhoa/api/employee/findByEmail/${email}`).then(resp => {
			$scope.employee = resp.data;
			initAutoComplete();
			if ($scope.employee.roles[0].roleID == "qlch") {
				$scope.admin = true;
			}
			console.log($scope.employee);
		});
	}

	//-----------------------------------------------//
	// --------- Kết thúc ca làm ----------- //

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
			let paymentDetail = angular.copy($scope.form);
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
	// khởi động
	//$scope.initialize();

	$scope.SetDefaultDate();

	/* Tìm kiếm theo ngày */
	let startDate = startDateFormat(new Date());
	let endDate = endDateFormat(new Date());
	$scope.findByDate(startDate, endDate, 0);

	// Tìm nhân viên theo email
	let email = document.getElementById('email').innerText;
	$scope.findEmployee(email)

})