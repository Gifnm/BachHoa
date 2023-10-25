

const app = angular.module("app", ["ui.bootstrap", "ui.tab.scroll"]);
app.controller("bill-ctrl", function ($scope, $http) {
    // gợi ý sản phẩm
    $scope.autocompleteInput = {
        autocomplete(inp, arr) {
            /*the autocomplete function takes two arguments,
            the text field element and an array of possible autocompleted values:*/
            let currentFocus;
            /*execute a function when someone writes in the text field:*/
            inp.addEventListener("input", function (e) {
                let a, b, i, val = this.value;
                /*close any already open lists of autocompleted values*/
                closeAllLists();
                if (!val) { return false; }
                currentFocus = -1;
                /*create a DIV element that will contain the items (values):*/
                a = document.createElement("DIV");
                a.setAttribute("id", this.id + "autocomplete-list");
                a.setAttribute("class", "autocomplete-items");
                /*append the DIV element as a child of the autocomplete container:*/
                this.parentNode.appendChild(a);
                /*for each item in the array...*/
                for (i = 0; i < arr.length; i++) {
                    /*check if the item starts with the same letters as the text field value:*/
                    if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                        /*create a DIV element for each matching element:*/
                        b = document.createElement("DIV");
                        /*make the matching letters bold:*/
                        b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                        b.innerHTML += arr[i].substr(val.length);
                        /*insert a input field that will hold the current array item's value:*/
                        b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                        /*execute a function when someone clicks on the item value (DIV element):*/
                        b.addEventListener("click", function (e) {
                            /*insert the value for the autocomplete text field:*/
                            inp.value = this.getElementsByTagName("input")[0].value;
                            $scope.addProductToBillDetail(inp.value);
                            //document.getElementById("productCode").focus()
                            /*close the list of autocompleted values,
                            (or any other open lists of autocompleted values:*/
                            closeAllLists();
                        });
                        a.appendChild(b);
                    }
                }
            });
            /*execute a function presses a key on the keyboard:*/
            inp.addEventListener("keydown", function (e) {
                let x = document.getElementById(this.id + "autocomplete-list");
                if (x) x = x.getElementsByTagName("div");
                if (e.keyCode == 40) {
                    /*If the arrow DOWN key is pressed,
                    increase the currentFocus variable:*/
                    currentFocus++;
                    /*and and make the current item more visible:*/
                    addActive(x);
                } else if (e.keyCode == 38) { //up
                    /*If the arrow UP key is pressed,
                    decrease the currentFocus variable:*/
                    currentFocus--;
                    /*and and make the current item more visible:*/
                    addActive(x);
                } else if (e.keyCode == 13) {
                    /*If the ENTER key is pressed, prevent the form from being submitted,*/
                    e.preventDefault();
                    if (currentFocus > -1) {
                        /*and simulate a click on the "active" item:*/
                        if (x) x[currentFocus].click();
                    }
                }
            });
            function addActive(x) {
                /*a function to classify an item as "active":*/
                if (!x) return false;
                /*start by removing the "active" class on all items:*/
                removeActive(x);
                if (currentFocus >= x.length) currentFocus = 0;
                if (currentFocus < 0) currentFocus = (x.length - 1);
                /*add class "autocomplete-active":*/
                x[currentFocus].classList.add("autocomplete-active");
            }
            function removeActive(x) {
                /*a function to remove the "active" class from all autocomplete items:*/
                for (let i = 0; i < x.length; i++) {
                    x[i].classList.remove("autocomplete-active");
                }
            }
            function closeAllLists(elmnt) {
                /*close all autocomplete lists in the document,
                except the one passed as an argument:*/
                var x = document.getElementsByClassName("autocomplete-items");
                for (let i = 0; i < x.length; i++) {
                    if (elmnt != x[i] && elmnt != inp) {
                        x[i].parentNode.removeChild(x[i]);
                    }
                }
            }
            /*execute a function when someone clicks in the document:*/
            document.addEventListener("click", function (e) {
                closeAllLists(e.target);
            });
        },

        // Lấy danh dách các sản phẩm
        init() {
            $http.get(`/product/getProductID`).then(resp => {
                products = resp.data;
                $scope.autocompleteInput.autocomplete(document.getElementById("productCode"), products);
            }).catch(error => {
                console.log('Error', error)
            });
        }
    }
    //------------------------------------------------//

    //XỬ LÝ THANH TOÁN

    // Xử lý bill
    $scope.bills = []; // mảng chứa tạm thời các bill
    $scope.billDetails = []; // mảng chứa tạm thời các billDetail
    $scope.listProduct = [];
    $scope.totalAmount = 0;
    $scope.amountReceivable = 0;
    $scope.roundAmountReceivable = 0;
    $scope.change = 0;
    $scope.discount = 0;
    $scope.invoiceID = "";
    $scope.sale = 0;
    $scope.discountName = "";
    $scope.discountDetail = {};

    // Khởi tạo 1 bill tạm đầu tiên
    let init = function () {
        $scope.bills.push({
            stt: 1,
            bill: createBill(),
            active: true
        });
        document.getElementById('print').disabled = true;
        console.log($scope.bills)
    }

    // tạo bill mới
    let createBill = function () {
        let storeID = parseInt(document.getElementById('storeID').innerText);
        let employeeID = parseInt(document.getElementById('employeeID').innerText);
        let bill = {};
        $http.get(`/bachhoa/api/store/findByID/${storeID}`).then(resp => {
            bill.billID = new Date().getTime().toString();
            bill.totalAmount = 0;
            bill.timeCreate = new Date().getTime();
            bill.cash = 0;
            bill.reduce = 0;
            bill.store = resp.data;
            $scope.invoiceID = bill.billID;
            $http.get(`/bachhoa/api/employee/findByID/${employeeID}`).then(resp => {
                bill.employee = resp.data;
            });
        });
        return bill;
    };

    // Tải lại dữ liệu bill
    $scope.selectBill = function (billID) {
        loadToBillDetail(billID);
        $scope.invoiceID = billID;
    };


    // lưu bill vào database
    let saveBillToDatabase = function (dataBill) {
        let url = `/bachhoa/api/bill/save`;
        $http.post(url, dataBill).then(resp => {
            console.log("Thêm thành công", resp);
            // lưu billDetail
            saveBillDetailToDatabse(dataBill.billID)
        }).catch(error => {
            console.log("Có lỗi xảy ra", error);
        });

    };


    let stt = 1;

    // thêm bill tạm vào mảng bills[]
    let addNewBill = function () {
        let bill = createBill();
        if ($scope.bills.length == 0) {
            $scope.bills.push({
                stt: 1,
                bill: bill,
                active: true
            });
        } else {
            stt = stt + 1;
            var stt_next = stt;
            $scope.bills.unshift({
                stt: stt_next,
                bill: bill,
                active: true
            });
        }
        loadToBillDetail(bill.billID)
        //$scope.invoiceID = bill.billID;
    };

    // chọn tab hoạt động
    let setAllInactive = function () {
        angular.forEach($scope.bills, function (bill) {
            bill.active = false;
        });
    };

    // xử lý button thêm bill tạm mới
    $scope.addBill = function () {
        setAllInactive();
        addNewBill();
        console.log($scope.bills)
    };
    // xóa bill tạm
    $scope.removeTab = function (index) {
        let item = $scope.bills[index];
        if ($scope.bills.length != 1 && index == $scope.bills.length - 1) {
            let item = $scope.bills[index - 1];
            $scope.invoiceID = item.bill.billID;
        } else if ($scope.bills.length != 1 && item.bill.billID == $scope.invoiceID) {
            let item = $scope.bills[index + 1];
            $scope.invoiceID = item.bill.billID;
        }
        $scope.bills.splice(index, 1);
        if ($scope.bills.length == 0) {
            addNewBill();
        }
        loadToBillDetail($scope.invoiceID);
    };

    //------------------------------------------------//

    // Xử lý billDetail
    // tải lại dự liệu trên bảng hóa đơn chi tiết
    let loadToBillDetail = function (billID) {
        if ($scope.billDetails.length == 0) {
            document.getElementById('print').disabled = true;
        }
        $scope.listProduct = [];
        angular.forEach($scope.billDetails, function (item) {
            if (item.billDetail.billID == billID) {
                item.billDetail.totalAmount = Math.round(item.billDetail.totalAmount);
                $scope.listProduct.push(item);
            }
        });
        total(billID);
        console.log($scope.listProduct)
    };

    // Thêm sản phẩm vào billDetail
    $scope.addProductToBillDetail = function (productID) {
        let item = $scope.billDetails.find(item => item.billDetail.productID == productID && item.billDetail.billID == $scope.invoiceID);
        //var billID = $scope.invoiceID;

        if (item) {
            updateBillDetail(productID, $scope.invoiceID);
            loadToBillDetail($scope.invoiceID);
            //$scope.productCode = '';
        } else {
            $http.get(`/product/getProductID`).then(resp => {
                let products = resp.data;
                let item = products.find(item => item == productID);
                if (item) {
                    $http.get(`/product/findByID/${productID}`).then(resp => {
                        let product = resp.data;
                        let temp = $scope.bills.find(item => item.bill.billID == $scope.invoiceID);
                        let bill = temp.bill;
                        let quantity = 1;
                        let storeID = 1;
                        $http.get(`/discount/findByProductIDAndStoreID/${productID}/${storeID}`).then(resp => {
                            //alert("run")
                            $scope.discountDetail = resp.data;
                            console.log($scope.discountDetail)
                            if ($scope.discountDetail.disID === "S25") {
                                //alert("s25")
                                $scope.sale = 0.25;
                                $scope.discountName = "25%";
                                //alert($scope.discountName)
                            } else if ($scope.discountDetail.disID === "S50") {
                                $scope.sale = 0.5;
                                $scope.discountName = "50%";
                                //alert("s50")
                            } else {
                                $scope.sale = 0;
                                $scope.discountName = "Không";
                            }

                            let totalMoney = Math.round(product.price + (product.price * (product.vat / 100)));
                            let discount = Math.round((product.price + (product.price * (product.vat / 100))) * $scope.sale);
                            let priceSale = totalMoney - discount;
                            let data = {
                                discountName: $scope.discountName,
                                sale: $scope.sale,
                                discount: Math.round((product.price + (product.price * (product.vat / 100))) * $scope.sale),
                                totalMoney: Math.round(product.price + (product.price * (product.vat / 100))),
                                billDetail: {
                                    billID: $scope.invoiceID,
                                    productID: productID,
                                    bill: bill,
                                    product: product,
                                    quantity: quantity,
                                    totalAmount: priceSale
                                }
                            };
                            $scope.billDetails.push(data);
                            loadToBillDetail($scope.invoiceID);
                            //$scope.productCode = '';
                        }).catch(error => {
                            console.log(error);
                        });

                    }).catch(error => {
                        console.log('Error', error)
                    })
                }
            }).catch(error => {
                console.log('Error', error)
            });
        }
    };

    //cập nhật billDetail khi tăng số lượng
    let updateBillDetail = function (productID, billID) {
        let item = $scope.billDetails.find(item => item.billDetail.productID == productID && item.billDetail.billID == billID);
        let index = $scope.billDetails.findIndex(item => item.billDetail.productID == productID && item.billDetail.billID == billID);
        item.billDetail.quantity = item.billDetail.quantity + 1;
        let quantity = item.billDetail.quantity;
        item.discount = (item.billDetail.product.price + (item.billDetail.product.price * (item.billDetail.product.vat / 100))) * quantity * item.sale;
        item.totalMoney = (item.billDetail.product.price + (item.billDetail.product.price * (item.billDetail.product.vat / 100))) * quantity;
        item.billDetail.totalAmount = item.totalMoney - item.discount;
        $scope.billDetails.splice(index, 1, item);
    };

    // Cập nhật billDetail khi tăng số lượng tại ô input số lượng
    $scope.updateBillDetailFromInput = function (productID, quantity, billID) {
        let item = $scope.billDetails.find(item => item.billDetail.productID == productID && item.billDetail.billID == billID);
        let index = $scope.billDetails.findIndex(item => item.billDetail.productID == productID && item.billDetail.billID == billID);
        item.billDetail.quantity = quantity;
        item.discount = (item.billDetail.product.price + (item.billDetail.product.price * (item.billDetail.product.vat / 100))) * quantity * item.sale;
        item.totalMoney = (item.billDetail.product.price + (item.billDetail.product.price * (item.billDetail.product.vat / 100))) * quantity;
        item.billDetail.totalAmount = item.totalMoney - item.discount;
        $scope.billDetails.splice(index, 1, item);
        loadToBillDetail(billID);
    };


    // Xóa sản phẩm trong billDetail
    $scope.deleteItem = function (productID, billID) {
        let index = $scope.billDetails.findIndex(item => item.billDetail.productID == productID && item.billDetail.billID == billID);
        $scope.billDetails.splice(index, 1);
        loadToBillDetail(billID);
    };

    //Tính tổng tiền của bill
    let total = function (billID) {
        $scope.totalAmount = 0;
        $scope.discount = 0;
        if ($scope.billDetails.length == 0) {
            $scope.totalAmount = 0;
            $scope.discount = 0;
            $scope.amountReceivable = 0;
        } else {
            angular.forEach($scope.billDetails, function (item) {
                if (item.billDetail.billID == billID) {
                    $scope.totalAmount += item.totalMoney;
                    $scope.discount += item.discount;
                }
            });
            $scope.totalAmount = Math.round($scope.totalAmount);
            $scope.discount = Math.round($scope.discount);
            $scope.amountReceivable = $scope.totalAmount - $scope.discount;
            $scope.roundAmountReceivable = Math.round($scope.amountReceivable / 1000) * 1000;
        }
        $scope.productCode = "";
        $scope.money = "";
        $scope.change = 0;
        document.getElementById("productCode").focus()
    };

    // Tính tiền thối lại
    $scope.cash = function (cash) {
        $scope.change = cash - $scope.roundAmountReceivable;
        document.getElementById('print').disabled = false;
    };

    // lưu billDetail vào database
    let saveBillDetailToDatabse = function (billID) {
        let url = `/bachhoa/api/billDetail/save/`;
        angular.forEach($scope.billDetails, function (item) {
            if (item.billDetail.billID == billID) {
                $http.post(url, item.billDetail).then(resp => {
                    console.log("Thêm thành công", resp);
                    var billDetail = resp.data;
                    window.location = "/print/" + billDetail.billID;
                }).catch(error => {
                    console.log("Có lỗi xảy ra", error);
                });
            }
        });
    };

    //------------------------------------------------//

    $scope.print = function () {
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




    //------------------------------------------------//
    //KHỞI CHẠY
    // Khởi chạy tạo bill đầu tiên
    init();

    // Khởi chạy lấy mảng gợi ý sản phẩm
    $scope.autocompleteInput.init();

    //------------------------------------------------//

});

