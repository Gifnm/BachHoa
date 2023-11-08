const app = angular.module("app", []);
app.controller("billsHistory-ctrl", function ($scope, $http) {
	$scope.items = [];
	$scope.form = {};
	$scope.billDetails = [];
	$scope.bill = {};
	$scope.totalAmountOfAllBills = 0;
	$scope.employee = {};
	$scope.discountDetail = {};

	$scope.initialize = function () {
		// load hóa đơn
		$http.get("/bachhoa/api/bill/all").then(resp => {
			$scope.items = resp.data;
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
			alert("Đã xóa hóa đơn!");
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
				$http.get(`/discount/findByProductIDAndStoreID/${item.product.productID}/${$scope.employee.store.storeID}`).then(resp => {
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
			$scope.totalAmountOfAllBills += item.bill.totalAmount;
		})
	}

	//Tìm hóa đơn
	$scope.find = function (billID) {
		if (billID == null || billID == undefined || billID == '') {
			$scope.initialize();
		} else {
			$scope.items = [];
			$http.get(`/bachhoa/api/bill/findBill/${billID}`).then(resp => {
				let bill = resp.data
				$scope.items.push(bill);
				Calc();
			}).catch(error => {
				console.log('Error', error)
			});
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
			alert('Ngày bắt đầu phải nhỏ hơn ngày kết thúc!');
			let date = new Date();
			$scope.fromDate = date;
			$scope.toDate = date;
			return;
		}
		let fromD = startDateFormat(fromDate);
		let toD = endtDateFormat(toDate);
		$http.get(`/bachhoa/api/bill/searchBetween/${fromD}/${toD}?index=${index}`).then(resp => {
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

	let endtDateFormat = function (value) {
		date = new Date(value);
		const day = date.getDate().toString().padStart(2, '0');
		const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months start at 0!
		const year = date.getFullYear();
		return `${year}-${month}-${day} ${23}:${59}:${59}`;
	}

	// gợi ý mã hóa đơn
	$scope.autocompleteInput = {
		autocomplete(inp, arr) {
			let currentFocus;
			inp.addEventListener("input", function (e) {
				let a, b, i, val = this.value;
				closeAllLists();
				if (!val) { return false; }
				currentFocus = -1;
				a = document.createElement("DIV");
				a.setAttribute("id", this.id + "autocomplete-list");
				a.setAttribute("class", "autocomplete-items");
				this.parentNode.appendChild(a);
				for (i = 0; i < arr.length; i++) {
					if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
						b = document.createElement("DIV");
						b.innerHTML = "<strong class='text-dark' >" + arr[i].substr(0, val.length) + "</strong>";
						b.innerHTML += arr[i].substr(val.length);
						b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
						b.addEventListener("click", function (e) {
							inp.value = this.getElementsByTagName("input")[0].value;
							$scope.find(inp.value);
							closeAllLists();
						});
						a.appendChild(b);
					}
				}
			});
			inp.addEventListener("keydown", function (e) {
				let x = document.getElementById(this.id + "autocomplete-list");
				if (x) x = x.getElementsByTagName("div");
				if (e.keyCode == 40) {
					currentFocus++;
					addActive(x);
				} else if (e.keyCode == 38) { //up
					currentFocus--;
					addActive(x);
				} else if (e.keyCode == 13) {
					e.preventDefault();
					if (currentFocus > -1) {
						if (x) x[currentFocus].click();
					}
				}
			});
			function addActive(x) {
				if (!x) return false;
				removeActive(x);
				if (currentFocus >= x.length) currentFocus = 0;
				if (currentFocus < 0) currentFocus = (x.length - 1);
				x[currentFocus].classList.add("autocomplete-active");
			}
			function removeActive(x) {
				for (let i = 0; i < x.length; i++) {
					x[i].classList.remove("autocomplete-active");
				}
			}
			function closeAllLists(elmnt) {
				var x = document.getElementsByClassName("autocomplete-items");
				for (let i = 0; i < x.length; i++) {
					if (elmnt != x[i] && elmnt != inp) {
						x[i].parentNode.removeChild(x[i]);
					}
				}
			}
			document.addEventListener("click", function (e) {
				closeAllLists(e.target);
			});
		},
		// Lấy danh sách mã hóa đơn
		init() {
			$http.get(`/bachhoa/api/bill/getBillID`).then(resp => {
				bill_ID = resp.data;
				$scope.autocompleteInput.autocomplete(document.getElementById("bill_ID"), bill_ID);
			}).catch(error => {
				console.log('Error', error)
			});
		}
	}
	//-----------------------------------------------//
	//	Tìm Nhân viên

	$scope.findEmployee = function (email) {
		$http.get(`/bachhoa/api/employee/findByEmail/${email}`).then(resp => {
			$scope.employee = resp.data;
			if ($scope.employee.role.roleID == "qlch") {
				$scope.admin = true;
			}
			console.log($scope.employee);
		});
	}

	//-----------------------------------------------//

	// khởi động
	//$scope.initialize();
	$scope.autocompleteInput.init();
	$scope.SetDefaultDate();

	/* Tìm kiếm theo ngày */
	let startDate = startDateFormat(new Date());
	let endDate = endtDateFormat(new Date());
	$scope.findByDate(startDate, endDate, 0);

	// Tìm nhân viên theo email
	let email = document.getElementById('email').innerText;
	$scope.findEmployee(email)

})