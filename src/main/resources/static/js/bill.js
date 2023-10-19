

var app = angular.module("app", ["ui.bootstrap", "ui.tab.scroll"]);
app.controller("bill-ctrl", function ($scope, $http) {
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
                            $scope.bills.add(inp.value)
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
            $http.get(`product/getProductID`).then(resp => {
                products = resp.data;
                $scope.autocompleteInput.autocomplete(document.getElementById("productCode"), products);
            }).catch(error => {
                console.log('Error', error)
            });
        }
    }
    $scope.bills = {
        billDetails: [],
        tongTien: 0,
        change: 0,
        // tìm sản phẩm -> thêm sp vào bảng sản phẩm ở bên trái -> tính tổng tiền
        add(productID) {
            var item = this.billDetails.find(item => item.product.productID == productID);
            if (item) {
                this.update(productID, item.quantity + 1);
            } else {
                $http.get(`product/getProductID`).then(resp => {
                    var products = resp.data;
                    var item = products.find(item => item == productID);                   
                    if (item) {
                        $http.get(`product/findByID/${productID}`).then(resp => {
                            var product = resp.data;
                            var quantity = 1;
                            var billDetail = {
                                product: product,
                                quantity: quantity,
                                totalAmount: (product.price * quantity) + (product.price * quantity * (product.vat / 100))
                            };
                            this.billDetails.unshift(billDetail);
                            this.total();
                            console.log('Success', resp)
                        }).catch(error => {
                            console.log('Error', error)
                        })
                    }
                }).catch(error => {
                    console.log('Error', error)
                });
            }
        },

        //cập nhật bill khi tăng số lượng
        update(productID, quantity) {
            var index = this.billDetails.findIndex(item => item.product.productID == productID);
            var item = this.billDetails.find(item => item.product.productID == productID);
            item.quantity = quantity;
            item.totalAmount = (item.product.price * quantity) + (item.product.price * quantity * (item.product.vat / 100));
            this.billDetails.splice(index, 1, item);
            this.tongTien = 0;
            this.total();
        },


        //Tính tổng tiền
        total() {

            if (this.billDetails.length == 0) {
                this.tongTien = 0;
                this.change = 0;
            } else {
                for (let i = 0; i < this.billDetails.length; i++) {
                    this.tongTien += this.billDetails[i].totalAmount;
                }
            }
        },

        // Tính tiền thối lại
        cash(cash) {
            this.change = cash - this.tongTien;
        },
        // Xóa bỏ sản phẩm trong bill
        deleteItem(productID) {
            var index = this.billDetails.findIndex(item => item.product.productID == productID);
            this.billDetails.splice(index, 1);
            this.tongTien = 0;
            this.total();
        },
    }
    $scope.autocompleteInput.init();

});

app.controller("add-tab-ctrl", function ($scope) {
    $scope.invoices = [{ id: 1, name: "Hóa đơn 1", active: true }];

    var id = 1;

    var addNewBill = function () {
        if ($scope.invoices.length == 0) {
            // var id = $scope.bills.length + 1;
            id = 1;
            $scope.invoices.push({
                id: id,
                name: "Hóa đơn " + id,
                active: true,
            });
        } else {
            id = id + 1;
            var id_next = id;
            $scope.invoices.unshift({
                id: id_next,
                name: "Hóa đơn " + id_next,
                active: true,
            });
        }
    };

    var setAllInactive = function () {
        angular.forEach($scope.invoices, function (bill) {
            bill.active = false;
        });
    };

    $scope.addBill = function () {
        setAllInactive();
        addNewBill();
    };

    $scope.removeTab = function (index) {
        $scope.invoices.splice(index, 1);
        if ($scope.invoices.length == 0) {
            addNewBill();
        }
    };

});
