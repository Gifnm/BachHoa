const app = angular.module("app", []);
app.controller("billsHistory-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};
	$scope.billDetail = [];
	$scope.bill = {};
	$scope.totalAmountOfAllBills = 0;
	$scope.employee = {};

	$scope.initialize = function() {
		// load hóa đơn
		$http.get("/bachhoa/api/bill/all").then(resp => {
			$scope.items = resp.data;
			Calc();
		});
		
	}

	// Hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
	}

	// Cập nhật sản phẩm
	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/bachhoa/api/bill/update/${item.billID}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.billID == item.billID);
			$scope.items[index] = item;
			$scope.initialize();
			alert("Update thành công!");
		}).catch(error => {
			alert("Lỗi update hóa đơn!");
			console.log("Error", error);
		});
	}

	// Xóa hóa đơn
	$scope.delete = function(item) {
		item = $scope.items.find(p => p.billID == item.billID);
		$http.put(`/bachhoa/api/bill/delete/${item.billID}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.billID == item.id);
			// $scope.items.splice(index, 1);
			$scope.items[index] = item;
			alert("Xóa thành công!");
			$scope.initialize();
			$scope.reset();
		}).catch(error => {
			alert("Hiện không thể xóa hóa đơn!");
			console.log("Error", error);
		});
	}

	// Xóa sản phẩm trong billDetail
	$scope.deleteItem = function(productID, billID) {
		let index = $scope.billDetails.findIndex(item => item.billDetail.productID == productID && item.billDetail.billID == billID);
		$scope.billDetails.splice(index, 1);
		loadToBillDetail(billID);
	};

	// Hiển chi tiết
	$scope.showDetail = function(billID) {
		$http.get(`/bachhoa/api/bill/findBill/${billID}`).then(resp => {
			$scope.bill = resp.data;
		}
		)
		$http.get(`/bachhoa/api/billDetail/findByBillID/${billID}`).then(resp => {
			$scope.billDetail = resp.data
			console.log($scope.billDetail);
		}
		)
	}

	/* Tính tổng thu */
	function Calc() {
		$scope.totalAmountOfAllBills = 0;
		angular.forEach($scope.items, function(item) {
			$scope.totalAmountOfAllBills += item.totalAmount;
		})
	}

	// xuất hóa đơn
	$scope.print = function() {
		let billID = $scope.invoiceID;
		let data = $scope.bills.find(item => item.bill.billID == billID);
		let item = data.bill;
		item.totalAmount = parseInt(document.getElementById('amountReceivable').innerText.replace(',', ''));
		item.cash = parseInt(document.getElementById('cash').value);
		item.reduce = parseInt(document.getElementById('discount').innerText.replace(',', ''));
		saveBillToDatabase(item);
		console.log(item)
		$scope.money = "";
	}

	//Tìm hóa đơn
	$scope.find = function(billID) {
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
	$scope.SetDefaultDate = function() {
		var date = new Date();

		$scope.fromDate = date;
		$scope.toDate = date;
	}

	// tìm kiếm theo ngày
	$scope.findByDate = function(fromDate, toDate) {
		let fromD = startDateFormat(fromDate);
		let toD = endtDateFormat(toDate);
		$http.get(`/bachhoa/api/bill/searchBetween/${fromD}/${toD}`).then(resp => {
			$scope.items = resp.data;
			Calc();
		}).catch(error => {
			console.log('Error', error)
		});
	}

	let startDateFormat = function(value) {
		date = new Date(value);
		const day = date.getDate().toString().padStart(2, '0');
		const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months start at 0!
		const year = date.getFullYear();
		// var hours = String(date.getHours()).padStart(2, '0');
		// var minutes = String(date.getMinutes()).padStart(2, '0');
		// var seconds = String(date.getSeconds()).padStart(2, '0');
	   
		return `${year}-${month}-${day} ${0}:${0}:${1}`;
	}

	let endtDateFormat = function(value) {
		date = new Date(value);
		const day = date.getDate().toString().padStart(2, '0');
		const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months start at 0!
		const year = date.getFullYear();
		// var hours = String(date.getHours()).padStart(2, '0');
		// var minutes = String(date.getMinutes()).padStart(2, '0');
		// var seconds = String(date.getSeconds()).padStart(2, '0');
	   
		return `${year}-${month}-${day} ${23}:${59}:${59}`;
	}

	// gợi ý mã hóa đơn
	$scope.autocompleteInput = {
		autocomplete(inp, arr) {
			let currentFocus;
			inp.addEventListener("input", function(e) {
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
						b.addEventListener("click", function(e) {
							inp.value = this.getElementsByTagName("input")[0].value;
							$scope.find(inp.value);
							closeAllLists();
						});
						a.appendChild(b);
					}
				}
			});
			inp.addEventListener("keydown", function(e) {
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
			document.addEventListener("click", function(e) {
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

	$scope.findEmployee = function(email) {
		$http.get(`/bachhoa/api/employee/findByEmail/${email}`).then(resp => {
			$scope.employee = resp.data;
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
	$scope.findByDate(startDate, endDate);

	// Tìm nhân viên theo email
	let email = document.getElementById('email').innerText;
	$scope.findEmployee(email)

})