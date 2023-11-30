const app_pay = angular.module("app", ["ui.bootstrap", "ui.tab.scroll"]);
app_pay.controller("pay-ctrl", function ($scope, $http) {
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
        initAutoComplete() {
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
    $scope.employee = {};

    var stt = 1;
    // Khởi tạo 1 bill tạm đầu tiên
    let init = function () {

        let email = document.getElementById('email').innerText;
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
            console.log($scope.employee)
            let test = sessionStorage.getItem("bills");
            loadBillFromSessionStorage();
            if (test == null || $scope.bills.length == 0) {
                $scope.bills = [];
                addNewBill();
            } else {
                loadBillFromSessionStorage();
                angular.forEach($scope.bills, function (item) { item.bill.employee = $scope.employee });
                setAllInactive();
                $scope.invoiceID = $scope.bills[0].bill.billID;
                stt = $scope.bills[0].stt;
                $scope.bills[0].active = true;
                loadBillDetailFromSessionStorage();
                loadToBillDetail($scope.bills[0].bill.billID);
            }
        });
        document.getElementById('print').disabled = true;
    };

    // tạo bill mới
    let createBill = function () {
        let bill = {};
        bill.billID = new Date().getTime().toString();
        bill.totalAmount = 0;
        bill.timeCreate = new Date().getTime();
        bill.cash = 0;
        bill.reduce = 0;
        return bill;
    };

    // Tải lại dữ liệu bill
    $scope.selectBill = function (billID) {
        //alert(billID)
        loadBillDetailFromSessionStorage();
        loadToBillDetail(billID);
        $scope.invoiceID = billID;
        //alert($scope.invoiceID)
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

    // thêm bill tạm vào mảng bills[]
    let addNewBill = function () {
        let listBill = $scope.bills;
        let employeeID = $scope.employee.employeeID;
        $http.get(`/bachhoa/api/store/findByID/${$scope.employee.store.storeID}`).then(resp => {
            let bill = createBill();
            bill.store = resp.data;
            $scope.invoiceID = bill.billID;
            console.log(resp.data)
            $http.get(`/bachhoa/api/employee/findByID/${employeeID}`).then(resp => {
                bill.employee = resp.data;
                //alert(listBill.length)
                if (listBill.length == 0) {
                    listBill.push({
                        stt: 1,
                        bill: bill,
                        active: true
                    });
                    //console.log(listBill)
                    saveBillToSessionStorage(listBill);
                } else {
                    stt = stt + 1;
                    var stt_next = stt;
                    listBill.unshift({
                        stt: stt_next,
                        bill: bill,
                        active: true
                    });
                    //console.log(listBill)
                    saveBillToSessionStorage(listBill);
                }
                loadBillFromSessionStorage();
                //$scope.invoiceID = bill.billID;
                loadToBillDetail(bill.billID);
            });
        });

    };

    let saveBillToSessionStorage = function (data) {
        const text = JSON.stringify(data);
        sessionStorage.setItem("bills", text);
    };

    let loadBillFromSessionStorage = function () {
        const obj = sessionStorage.getItem("bills");
        const list = JSON.parse(obj);
        //console.log(list);
        $scope.bills = list;

        //console.log($scope.bills);
    };

    // chọn tab hoạt động
    let setAllInactive = function () {
        //console.log($scope.bills);
        angular.forEach($scope.bills, function (bill) {
            bill.active = false;
        });
    };

    // xử lý button thêm bill tạm mới
    $scope.addBill = function () {
        setAllInactive();
        addNewBill();
    };
    // xóa bill tạm
    $scope.removeTab = function (index) {
        var item = $scope.bills[index];
        //alert(item.bill.billID)
        //alert(index)
        if ($scope.bills.length != 1 && (item.bill.billID == $scope.invoiceID && index == $scope.bills.length - 1)) {
            let bill = $scope.bills[index - 1];
            $scope.invoiceID = bill.bill.billID;
        } else if ($scope.bills.length != 1 && index != $scope.bills.length - 1) {
            let bill = $scope.bills[index + 1];
            $scope.invoiceID = bill.bill.billID;
        }

        //alert($scope.invoiceID)
        let wide = $scope.billDetails.length;
        for (let i = 0; i < wide; i++) {
            let index = $scope.billDetails.findIndex(a => a.billDetail.billID == item.bill.billID);
            //alert(index)
            if (index >= 0) {
                $scope.billDetails.splice(index, 1);
                saveBillDetailToSessionStorage($scope.billDetails);
                loadBillDetailFromSessionStorage();
            }
        }
        $scope.bills.splice(index, 1);
        saveBillToSessionStorage($scope.bills);
        loadBillFromSessionStorage();
        if ($scope.bills.length == 0) {
            addNewBill();
        }
        loadToBillDetail($scope.invoiceID);
    };


    //------------------------------------------------//

    // Xử lý billDetail


    let saveBillDetailToSessionStorage = function (data) {
        const text = JSON.stringify(data);
        sessionStorage.setItem("billDetails", text);
    };

    let loadBillDetailFromSessionStorage = function () {
        const obj = sessionStorage.getItem("billDetails");
        const list = JSON.parse(obj);
        if (list == null) {
            $scope.billDetails = [];
        } else {
            $scope.billDetails = list;
        }

    };

    // tải lại dự liệu trên bảng hóa đơn chi tiết
    let loadToBillDetail = function (billID) {
        if ($scope.billDetails.length == 0) {
            document.getElementById('print').disabled = true;
        }
        $scope.listProduct = [];
        angular.forEach($scope.billDetails, function (item) {
            if (item.billDetail.billID == billID) {
                //item.billDetail.totalAmount = Math.round(item.billDetail.totalAmount);
                $scope.listProduct.push(item);
            }
        });
        total(billID);
        //onsole.log($scope.listProduct)
    };

    // Thêm sản phẩm vào billDetail
    $scope.addProductToBillDetail = function (productID) {
        let item = $scope.billDetails.find(item => item.billDetail.productID == productID && item.billDetail.billID == $scope.invoiceID);
        //var billID = $scope.invoiceID;

        if (item) {
            updateBillDetail(productID, $scope.invoiceID);
            saveBillDetailToSessionStorage($scope.billDetails);
            loadBillDetailFromSessionStorage();
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
                        let storeID = $scope.employee.store.storeID;
                        $http.get(`/bachhoa/api/discount/findDiscountIsActive/${productID}/${storeID}`).then(resp => {
                            //alert("run")
                            $scope.discountDetail = resp.data;
                            //console.log($scope.discountDetail)
                            if ($scope.discountDetail.disID === "S25") {
                                //alert("s25")
                                $scope.sale = 0.25;
                                $scope.discountName = "25%";
                                //alert($scope.discountName)
                            } else if ($scope.discountDetail.disID === "S50") {
                                $scope.sale = 0.5;
                                $scope.discountName = "50%";
                                //alert("s50")
                            } else if ($scope.discountDetail.disID === "2T1") {
                                $scope.discountName = "2 tặng 1";
                                $scope.sale = 0;
                            } else {
                                $scope.sale = 0;
                                $scope.discountName = "Không";
                            }

                            let totalMoney = product.price + (product.price * (product.vat / 100));
                            let discount = (product.price + (product.price * (product.vat / 100))) * $scope.sale;
                            let priceSale = totalMoney - discount;
                            let data = {
                                discountName: $scope.discountName,
                                sale: $scope.sale,
                                discount: (product.price + (product.price * (product.vat / 100))) * $scope.sale,
                                totalMoney: product.price + (product.price * (product.vat / 100)),
                                billDetail: {
                                    billID: $scope.invoiceID,
                                    productID: productID,
                                    bill: bill,
                                    product: product,
                                    quantity: quantity,
                                    quantityGift: 0,
                                    totalAmount: priceSale
                                },
                            };
                            $scope.billDetails.unshift(data);
                            //console.log($scope.billDetails);
                            saveBillDetailToSessionStorage($scope.billDetails);
                            loadBillDetailFromSessionStorage();
                            //console.log($scope.billDetails);
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
        let quantity = item.billDetail.quantity + item.billDetail.quantityGift;
        if (item.discountName == "2 tặng 1" && (quantity % 3) === 0) {
            item.billDetail.quantity = item.billDetail.quantity - 1;
            item.billDetail.quantityGift = item.billDetail.quantityGift + 1;
            if (item.billDetail.quantityGift === 1) {
                let gift = angular.copy(item);
                gift.billDetail.quantity = item.billDetail.quantityGift;
                gift.sale = 0;
                gift.discount = 0;
                gift.totalMoney = 0;
                gift.billDetail.totalAmount = 0;
                gift.billDetail.product.price = 0;
                gift.billDetail.product.productName = "Quà tặng: " + gift.billDetail.product.productName;
                $scope.billDetails.push(gift);
            } else {
                let i = $scope.billDetails.findIndex(
                    o => o.billDetail.productID == productID
                        && o.billDetail.billID == billID
                        && o.billDetail.product.productName.substr(0, 8) == "Quà tặng");
                let giftData = $scope.billDetails.find(
                    o => o.billDetail.productID == productID
                        && o.billDetail.billID == billID
                        && o.billDetail.product.productName.substr(0, 8) == "Quà tặng");
                //console.log(giftData)
                giftData.billDetail.quantity++;
                $scope.billDetails.splice(i, 1, giftData);
            }
            item.discount = (item.billDetail.product.price + (item.billDetail.product.price * (item.billDetail.product.vat / 100))) * item.billDetail.quantityGift;
        } else if (item.discountName == "25%" || item.discountName == "50%") {
            item.discount = (item.billDetail.product.price + (item.billDetail.product.price * (item.billDetail.product.vat / 100))) * quantity * item.sale;
        }
        item.totalMoney = (item.billDetail.product.price + (item.billDetail.product.price * (item.billDetail.product.vat / 100))) * quantity;
        item.billDetail.totalAmount = item.totalMoney - item.discount;
        $scope.billDetails.splice(index, 1, item);
        console.log($scope.billDetails)
    };

    // Cập nhật billDetail khi tăng số lượng tại ô input số lượng
    $scope.updateBillDetailFromInput = function (productID, quantity, billID) {
        let item = $scope.billDetails.find(item => item.billDetail.productID == productID && item.billDetail.billID == billID);
        let index = $scope.billDetails.findIndex(item => item.billDetail.productID == productID && item.billDetail.billID == billID);
        item.billDetail.quantity = quantity;
        if (item.discountName == "2 tặng 1" && quantity >= 3) {
            item.billDetail.quantityGift = 0;
            item.billDetail.quantityGift = item.billDetail.quantityGift + Math.floor(item.billDetail.quantity / 3);
            item.billDetail.quantity = item.billDetail.quantity - item.billDetail.quantityGift;

            let giftData = $scope.billDetails.find(
                o => o.billDetail.productID == productID
                    && o.billDetail.billID == billID
                    && o.billDetail.product.productName.substr(0, 8) == "Quà tặng");
            if (item.billDetail.quantityGift > 0 && !giftData) {
                let gift = angular.copy(item);
                gift.billDetail.quantity = item.billDetail.quantityGift;
                gift.sale = 0;
                gift.discount = 0;
                gift.totalMoney = 0;
                gift.billDetail.totalAmount = 0;
                gift.billDetail.product.price = 0;
                gift.billDetail.product.productName = "Quà tặng: " + gift.billDetail.product.productName;
                $scope.billDetails.push(gift);
            } else {
                let i = $scope.billDetails.findIndex(
                    o => o.billDetail.productID == productID
                        && o.billDetail.billID == billID
                        && o.billDetail.product.productName.substr(0, 8) == "Quà tặng");
                giftData.billDetail.quantity = item.billDetail.quantityGift;
                $scope.billDetails.splice(i, 1, giftData);
            }
            item.discount = (item.billDetail.product.price + (item.billDetail.product.price * (item.billDetail.product.vat / 100))) * item.billDetail.quantityGift;
        } else if (item.discountName == "2 tặng 1" && quantity < 2) {
            let i = $scope.billDetails.findIndex(
                o => o.billDetail.productID == productID
                    && o.billDetail.billID == billID
                    && o.billDetail.product.productName.substr(0, 8) == "Quà tặng");
            $scope.billDetails.splice(i, 1);
            item.billDetail.quantityGift = 0;
        } else if (item.discountName == "25%" || item.discountName == "50%") {
            item.discount = (item.billDetail.product.price + (item.billDetail.product.price * (item.billDetail.product.vat / 100))) * quantity * item.sale;
        }
        item.totalMoney = (item.billDetail.product.price + (item.billDetail.product.price * (item.billDetail.product.vat / 100))) * quantity;
        item.billDetail.totalAmount = item.totalMoney - item.discount;
        $scope.billDetails.splice(index, 1, item);
        console.log($scope.billDetails)
        saveBillDetailToSessionStorage($scope.billDetails);
        loadBillDetailFromSessionStorage();
        loadToBillDetail(billID);
    };

    // Tăng số lượng sản phẩm lên 1
    $scope.increase = function (productID, billID) {
        updateBillDetail(productID, billID);
        saveBillDetailToSessionStorage($scope.billDetails);
        loadBillDetailFromSessionStorage();
        loadToBillDetail($scope.invoiceID);
    }

    // Giảm số lượng sản phẩm xuống 1
    $scope.reduce = function (productID, quantity, billID) {
        let sl = quantity - 1;
        $scope.updateBillDetailFromInput(productID, sl, billID);
    }


    // Xóa sản phẩm trong billDetail
    $scope.deleteItem = function (index, billID, productID) {
        if ($scope.billDetails[index].billDetail.product.productName.substr(0, 8) == "Quà tặng") {
            let billDetail = $scope.billDetails.find(item => item.billDetail.productID == productID && item.billDetail.billID == billID);
            billDetail.billDetail.quantityGift = 0;
            $scope.updateBillDetailFromInput(productID, billDetail.billDetail.quantity, billID);
        }
        $scope.billDetails.splice(index, 1);
        saveBillDetailToSessionStorage($scope.billDetails);
        loadBillDetailFromSessionStorage();
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
            $scope.roundAmountReceivable = 0;
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
        //document.getElementById("productCode").focus()
    };

    // Tính tiền thối lại
    $scope.cash = function (cash) {
        $scope.change = cash - $scope.roundAmountReceivable;
        document.getElementById('print').disabled = false;
    };

    // lưu billDetail vào database
    let saveBillDetailToDatabse = function (billID) {
        let url = `/bachhoa/api/billDetail/save/`;
        let isLast = false;
        angular.forEach($scope.billDetails, function (item) {
            if (item.billDetail.billID == billID && item.billDetail.product.productName.substr(0, 8) != "Quà tặng") {
                $http.post(url, item.billDetail).then(resp => {
                    console.log("Thêm thành công", resp);
                    $http.get(`/product/findByID/${item.billDetail.productID}`).then(resp => {
                        let product = resp.data;
                        console.log(product);
                        product.inventory = product.inventory - (item.billDetail.quantity + item.billDetail.quantityGift);
                        console.log(product);
                        $http.put(`/bachhoa/api/products/update`, product)
                        window.location = "/print/" + billID;
                    })
                }).catch(error => {
                    console.log("Có lỗi xảy ra", error);
                });
            }
            let i = $scope.billDetails.indexOf(item);
            if (i == $scope.billDetails.length - 1) {
                isLast = true;
            }
        });
        if (isLast) {
            let index = $scope.bills.findIndex(item => item.bill.billID == billID);
            $scope.removeTab(index);
        }
    };

    //------------------------------------------------//
    $scope.errorAlert = "true";

    $scope.print = function () {
        let billID = $scope.invoiceID;
        //alert('thong bao tai in' + billID)
        let data = $scope.bills.find(item => item.bill.billID == billID);
        let item = data.bill;
        item.totalAmount = parseInt(document.getElementById('totalAmount').innerText.replaceAll(',', ''));
        item.cash = parseInt(document.getElementById('cash').value);
        item.reduced = parseInt(document.getElementById('discount').innerText.replaceAll(',', ''));
        item.timeCreate = new Date().getTime();
        if (item.cash < $scope.roundAmountReceivable) {
            //alert('loi')
            document.getElementById('errorAlert').setAttribute("style", "display: block;");
            document.getElementById('errorAlert').setAttribute("aria-modal", true);
            document.getElementById('errorAlert').setAttribute("role", "dialog");
            $scope.dialog = "show";
        } else {
            saveBillToDatabase(item);
            //console.log(item)
            $scope.money = "";
        }

    }

    $scope.closeModal = function () {
        document.getElementById('errorAlert').setAttribute("style", "display: none;");
        document.getElementById('errorAlert').setAttribute("aria-modal", false);
        $scope.dialog = "";
        document.getElementById("cash").focus()
    }
    //---------------------------------//
    // Active tab
    $scope.isActive = 'isPay';
    // cập nhật hồ sơ và kết ca

    //cập nhật hồ sơ
    $scope.admin = false;
    $scope.dataEmployee = {};
    // Kết ca
    $scope.totalMoneyYouPay = 0;
    $scope.formMoney = {};
    $scope.TotalMoneytoPay = 0;

    // Cập nhật thông tin nhân viên
    $scope.updateEmployee = function () {
        let em = angular.copy($scope.dataEmployee);
        const AGE = document.getElementById("age").value;

        const ThisYear = new Date();
        const EmployeeBorn = new Date(AGE);
        const Timelines = ThisYear - EmployeeBorn;
        if (em.email == null || em.email == "") {
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
        } else if (em.address == null || em.address == "") {
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
        data.email = em.email;
        data.age = em.age;
        data.address = em.address;
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

    //------------------------------------------------//

    //KHỞI CHẠY
    // Khởi chạy tạo bill đầu tiên
    init();
    // Khởi chạy lấy mảng gợi ý sản phẩm
    $scope.autocompleteInput.initAutoComplete();

});

