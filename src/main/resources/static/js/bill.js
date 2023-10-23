

var app = angular.module("app", ["ui.bootstrap", "ui.tab.scroll"]);
app.controller("bill-ctrl", function ($scope, $http) {
    // gợi ý sản phẩm
    $scope.autocompleteInput = {
        autocomplete(inp, arr) {
            /*the autocomplete function takes two arguments,
            the text field element and an array of possible autocompleted values:*/
            var currentFocus;
            /*execute a function when someone writes in the text field:*/
            inp.addEventListener("input", function (e) {
                var a, b, i, val = this.value;
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
                var x = document.getElementById(this.id + "autocomplete-list");
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
                for (var i = 0; i < x.length; i++) {
                    x[i].classList.remove("autocomplete-active");
                }
            }
            function closeAllLists(elmnt) {
                /*close all autocomplete lists in the document,
                except the one passed as an argument:*/
                var x = document.getElementsByClassName("autocomplete-items");
                for (var i = 0; i < x.length; i++) {
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
    $scope.change = 0;
    $scope.discount = 0;
    $scope.invoiceID = "";

    // Khởi tạo 1 bill tạm đầu tiên
    var init = function () {
        $scope.bills.push({
            stt: 1,
            bill: createBill(),
            active: true
        });
        document.getElementById('print').disabled = true;
        console.log($scope.bills)
    }

    // tạo bill mới
    var createBill = function () {
        var storeID = parseInt(document.getElementById('storeID').innerText);
        var employeeID = parseInt(document.getElementById('employeeID').innerText);
        var bill = {};
        $http.get(`/bachhoa/api/store/findByID/${storeID}`).then(resp => {
            bill.billID = new Date().getTime().toString();
            bill.totalAmount = 0;
            bill.createDate = new Date();
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
        // $scope.productCode = "";
        // $scope.totalMoney = 0;
        // $scope.cash = "";
        // $scope.change = 0;
        // $scope.discount = 0;
        //alert(billID)
    };


    // lưu bill vào database
    var saveBillToDatabase = function (dataBill) {
        var url = `/bachhoa/api/bill/save`;
        $http.post(url, dataBill).then(resp => {
            console.log("Thêm thành công", resp);
            // lưu billDetail
            saveBillDetailToDatabse(dataBill.billID)
        }).catch(error => {
            console.log("Có lỗi xảy ra", error);
        });

    };


    var stt = 1;

    // thêm bill tạm vào mảng bills[]
    var addNewBill = function () {
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
    var setAllInactive = function () {
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
        var item = $scope.bills[index];
        if ($scope.bills.length != 1 && index == $scope.bills.length - 1) {
            var item = $scope.bills[index - 1];
            $scope.invoiceID = item.bill.billID;
        } else if ($scope.bills.length != 1 && item.bill.billID == $scope.invoiceID) {
            var item = $scope.bills[index + 1];
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
    var loadToBillDetail = function (billID) {
        if ($scope.billDetails.length == 0) {
            document.getElementById('print').disabled = true;
        }
        $scope.listProduct = [];
        angular.forEach($scope.billDetails, function (billDetail) {
            if (billDetail.billID == billID) {
                $scope.listProduct.push(billDetail);
            }
        });
        total(billID);
        console.log($scope.listProduct)
    };

    // Thêm sản phẩm vào billDetail
    $scope.addProductToBillDetail = function (productID) {
        var item = $scope.billDetails.find(item => item.product.productID == productID && item.billID == $scope.invoiceID);
        //var billID = $scope.invoiceID;

        if (item) {
            updateBillDetail(productID, $scope.invoiceID);
            loadToBillDetail($scope.invoiceID);
            //$scope.productCode = '';
        } else {
            $http.get(`/product/getProductID`).then(resp => {
                var products = resp.data;
                var item = products.find(item => item == productID);
                if (item) {
                    $http.get(`/product/findByID/${productID}`).then(resp => {
                        var product = resp.data;
                        var temp = $scope.bills.find(item => item.bill.billID == $scope.invoiceID);
                        var bill = temp.bill;
                        var quantity = 1;
                        var data = {
                            billID: $scope.invoiceID,
                            productID: productID,
                            bill: bill,
                            product: product,
                            quantity: quantity,
                            totalAmount: product.price + (product.price * (product.vat / 100))
                        };
                        $scope.billDetails.push(data);
                        loadToBillDetail($scope.invoiceID);
                        //$scope.productCode = '';
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
    var updateBillDetail = function (productID, billID) {
        var item = $scope.billDetails.find(item => item.productID == productID && item.billID == billID);
        var index = $scope.billDetails.findIndex(item => item.productID == productID && item.billID == billID);
        item.quantity = item.quantity + 1;
        var quantity = item.quantity;
        item.totalAmount = (item.product.price * quantity) + (item.product.price * quantity * (item.product.vat / 100));
        $scope.billDetails.splice(index, 1, item);
    };

    // Cập nhật billDetail khi tăng số lượng tại ô input số lượng
    $scope.updateBillDetailFromInput = function (productID, quantity, billID) {
        var item = $scope.billDetails.find(item => item.productID == productID && item.billID == billID);
        var index = $scope.billDetails.findIndex(item => item.product.productID == productID);
        item.quantity = quantity;
        item.totalAmount = (item.product.price * quantity) + (item.product.price * quantity * (item.product.vat / 100));
        $scope.billDetails.splice(index, 1, item);
        loadToBillDetail(billID);
    };


    // Xóa sản phẩm trong billDetail
    $scope.deleteItem = function (productID, billID) {
        var index = $scope.billDetails.findIndex(item => item.productID == productID && item.billID == billID);
        $scope.billDetails.splice(index, 1);
        loadToBillDetail(billID);
    };

    //Tính tổng tiền của bill
    var total = function (billID) {
        $scope.totalAmount = 0;
        if ($scope.billDetails.length == 0) {
            $scope.totalAmount = 0;
            $scope.discount = 0;
            $scope.amountReceivable = 0;
        } else {
            angular.forEach($scope.billDetails, function (billDetail) {
                if (billDetail.billID == billID) {
                    $scope.totalAmount += billDetail.totalAmount;
                }
            });
            $scope.amountReceivable = $scope.totalAmount - $scope.discount;
        }
        $scope.productCode = "";
        $scope.money = "";
        $scope.change = 0;
    };

    // Tính tiền thối lại
    $scope.cash = function (cash) {
        $scope.change = cash - $scope.totalAmount;
        document.getElementById('print').disabled = false;
    };

    // lưu billDetail vào database
    var saveBillDetailToDatabse = function (billID) {
        var url = `/bachhoa/api/billDetail/save/`;
        angular.forEach($scope.billDetails, function (billDetail) {
            if (billDetail.billID == billID) {
                $http.post(url, billDetail).then(resp => {
                    console.log("Thêm thành công", resp);
                }).catch(error => {
                    console.log("Có lỗi xảy ra", error);
                });
            }
        });
    };

    //------------------------------------------------//

    $scope.print = function () {
        //var billID = document.getElementById('billID').innerText;
        var billID = $scope.invoiceID;
        var data = $scope.bills.find(item => item.bill.billID == billID);
        var item = data.bill;
        item.totalAmount = parseInt(document.getElementById('totalAmount').innerText.replace(',', ''));
        item.cash = parseInt(document.getElementById('cash').value);
        item.reduce = parseInt(document.getElementById('discount').innerText.replace(',', ''));
        saveBillToDatabase(item);
        console.log(item)
    }




    //------------------------------------------------//
    //KHỞI CHẠY
    // Khởi chạy tạo bill đầu tiên
    init();

    // Khởi chạy lấy mảng gợi ý sản phẩm
    $scope.autocompleteInput.init();

    //------------------------------------------------//

});


















// các chức năng thanh toán
// $scope.pay = {
//     billDetails: [],
//     tongTien: 0,
//     change: 0,
//     reduce: 0,
//     cash: 0,
//     stt: 1,

//     // Tạo hóa đơn mới




//     //--------------------

//     createBillDetail(productID, quantity, totalMoney) {
//         billDetail = {
//             //billDetailID: new Date().getTime().toString(),
//             billID: document.getElementById('billID').innerText,
//             productID: productID,
//             quantity: quantity,
//             totalMoney: totalMoney
//         }

//     },



//     add(productID) {
//         var item = this.billDetails.find(item => item.product.productID == productID);
//         var billID = document.getElementById('billID').innerText;
//         if (item) {
//             this.update(productID);
//         } else {
//             $http.get(`/product/getProductID`).then(resp => {
//                 var products = resp.data;
//                 var item = products.find(item => item == productID);
//                 if (item) {
//                     $http.get(`/product/findByID/${productID}`).then(resp => {
//                         var product = resp.data;
//                         var temp = this.bills.find(item => item.bill.billID == billID);
//                         var bill = temp.bill;
//                         //saveBill(bill);
//                         var quantity = 1;
//                         var billDetail = {
//                             billID: billID,
//                             productID: productID,
//                             bill: bill,
//                             product: product,
//                             quantity: quantity,
//                             totalMoney: product.price + (product.price * (product.vat / 100))
//                         };
//                         saveBillDetail(billDetail);
//                         //this.loadToBillDetail(billID);
//                         //this.total();
//                     }).catch(error => {
//                         console.log('Error', error)
//                     })
//                 }
//             }).catch(error => {
//                 console.log('Error', error)
//             });
//         }
//     },





//     // add(productID) {
//     //     var item = this.billDetails.find(item => item.product.productID == productID);
//     //     if (item) {
//     //         this.update(productID, item.quantity + 1);
//     //     } else {
//     //         $http.get(`product/getProductID`).then(resp => {
//     //             var products = resp.data;
//     //             var item = products.find(item => item == productID);
//     //             if (item) {
//     //                 $http.get(`product/findByID/${productID}`).then(resp => {
//     //                     var product = resp.data;
//     //                     var quantity = 1;
//     //                     var billDetail = {
//     //                         product: product,
//     //                         quantity: quantity,
//     //                         totalAmount: (product.price * quantity) + (product.price * quantity * (product.vat / 100))
//     //                     };
//     //                     this.billDetails.unshift(billDetail);
//     //                     this.total();
//     //                     console.log('Success', resp)
//     //                 }).catch(error => {
//     //                     console.log('Error', error)
//     //                 })
//     //             }
//     //         }).catch(error => {
//     //             console.log('Error', error)
//     //         });
//     //     }
//     // },

//     //cập nhật billDetail khi tăng số lượng
//     update(productID) {
//         var billID = document.getElementById('billID').innerText;
//         var item = this.billDetails.find(item => item.product.productID == productID);
//         var quantity = item.billDetail.quantity + 1;
//         var totalMoney = (item.product.price * quantity) + (item.product.price * quantity * (item.product.vat / 100));
//         var data = {
//             billDetailID: item.billDetail.billDetailID,
//             billID: item.billDetail.billID,
//             productID: productID,
//             quantity: quantity,
//             totalMoney: totalMoney
//         }
//         var url = `/bachhoa/api/billDetail/update/${item.billDetail.billDetailID}`;
//         $http.put(url, data).then(resp => {
//             console.log("Sucess", resp)
//         }).catch(error => {
//             console.log("Error", error)
//         });
//         this.loadToBillDetail(billID);
//         this.total();
//     },


//     //Tính tổng tiền
//     total() {

//         if (this.billDetails.length == 0) {
//             this.tongTien = 0;
//             this.change = 0;
//         } else {
//             for (let i = 0; i < this.billDetails.length; i++) {
//                 this.tongTien += this.billDetails[i].billDetail.totalMoney;
//             }
//         }
//     },

//     // Tính tiền thối lại
//     cash(cash) {
//         this.change = cash - this.tongTien;
//     },
//     // Xóa bỏ sản phẩm trong billDetail
//     // deleteItem(productID) {
//     //     var index = this.billDetails.findIndex(item => item.product.productID == productID);
//     //     this.billDetails.splice(index, 1);
//     //     this.tongTien = 0;
//     //     this.total();
//     // },
//     deleteItem(productID) {
//         var billID = document.getElementById('billID').innerText;
//         var item = this.billDetails.find(item => item.product.productID == productID);
//         $http.delete(`/bachhoa/api/billDetail/delete/${item.billDetail.billDetailID}`)
//             .then(this.loadToBillDetail(billID));
//     },
// }




// thêm tab nhập hóa đơn
// app.controller("add-tab-ctrl", function ($scope) {
//     $scope.invoices = [{ stt: 1, id: new Date().getTime().toString(), name: "Hóa đơn 1", active: true }];

//     var id = 1;

//     var addNewBill = function () {
//         if ($scope.invoices.length == 0) {
//             // var id = $scope.bills.length + 1;
//             id = 1;
//             $scope.invoices.push({
//                 id: id,
//                 name: "Hóa đơn " + id,
//                 active: true,
//             });
//         } else {
//             id = id + 1;
//             var id_next = id;
//             $scope.invoices.unshift({
//                 id: id_next,
//                 name: "Hóa đơn " + id_next,
//                 active: true,
//             });
//         }
//     };

//     var setAllInactive = function () {
//         angular.forEach($scope.invoices, function (bill) {
//             bill.active = false;
//         });
//     };

//     $scope.addBill = function () {
//         setAllInactive();
//         addNewBill();
//         $scope.pay.createBill();
//     };

//     $scope.removeTab = function (index) {
//         $scope.invoices.splice(index, 1);
//         if ($scope.invoices.length == 0) {
//             addNewBill();
//         }
//     };

// });

// app.controller("add-tab-ctrl", function ($scope) {
//     $scope.invoices = [{ stt: 1, id: new Date().getTime().toString(), name: "Hóa đơn 1", active: true }];

//     var id = 1;

//     var addNewBill = function () {
//         if ($scope.invoices.length == 0) {
//             // var id = $scope.bills.length + 1;
//             id = 1;
//             $scope.invoices.push({
//                 id: id,
//                 name: "Hóa đơn " + id,
//                 active: true,
//             });
//         } else {
//             id = id + 1;
//             var id_next = id;
//             $scope.invoices.unshift({
//                 id: id_next,
//                 name: "Hóa đơn " + id_next,
//                 active: true,
//             });
//         }
//     };

//     var setAllInactive = function () {
//         angular.forEach($scope.invoices, function (bill) {
//             bill.active = false;
//         });
//     };

//     $scope.addBill = function () {
//         setAllInactive();
//         addNewBill();
//         $scope.pay.createBill();
//     };

//     $scope.removeTab = function (index) {
//         $scope.invoices.splice(index, 1);
//         if ($scope.invoices.length == 0) {
//             addNewBill();
//         }
//     };

// });

