import {} from './addTab.js';
var app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http) {
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
                this.suggest(productID);
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

        availableKeywords: [],
        result: [],


        init() {
            alert('run1')
            $http.get(`product/getProductID`).then(resp => {
                availableKeywords = resp.data;
                console.log('Success', resp)
            }).catch(error => {
                console.log('Error', error)
            });
        },

        suggest(input) {
            if (input.length) {
                result = availableKeywords.filter((keyword) => {
                    return keyword.toLowerCase().includes(input.toLowerCase());
                });
            }
            resultsBox = document.querySelector(".result-box");
            const content = result.map((list) => {
                return "<li ng-click=\"bills.selectInput()\">" + list + "</li>";
            });
            resultsBox.innerHTML = "<ul>" + content.join('') + "</ul>";

            if (!result.length) {
                resultsBox.innerHTML = '';
            }
        },

        selectInput() {
            alert('run')
            resultsBox = document.querySelector(".result-box");
            // inputBox = document.getElementById("input-box");
            // inputBox.value = list.innerHTML;
            // $scope.productID = productID;
            resultsBox.innerHTML = '';
        },


    }
    $scope.bills.init();
});
