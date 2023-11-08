const app = angular.module("app", []);
app.controller("paymentHistory-ctrl", function ($scope, $http) {
    $scope.listEmployee = [];
    $scope.payment = {};
    // $scope.bills = [];
    // $scope.listBill = [];
    $scope.items = [];

    // tìm lịch sử nộp tiền theo nhân viên
    $scope.loadHistoryByEmployee = function (employeeID) {
        let ID = parseInt(employeeID.substr(0, 1));
        $http.get(`/bachhoa/api/paymentHistory/findByEmployee/${ID}`).then(resp => {
            $scope.items = resp.data;
        })
    }
    // hiển thị tất cả các lịch sử nộp tiền
    let loadHistory = function () {
        $http.get(`/bachhoa/api/paymentHistory/getAll`).then(resp => {
            $scope.items = resp.data;
            angular.forEach($scope.items, function (item) {
                item.timePay = dateFormat(item.timePay);
                if (item.timeReceived != null) {
                    item.timeReceived = dateFormat(item.timeReceived);
                }
            })
        });
    }
    // hiển thị chi tiết nộp tiền
    $scope.showPaymentDetail = function (id) {
        $http.get(`/bachhoa/api/paymentDetail/findByPaymentID/${id}`).then(resp => {
            $scope.payment = resp.data;
            console.log($scope.payment);
        }).catch(error => {
            console.log(error);
        })
    }

    let SetDefaultDate = function () {
        let date = new Date();
        $scope.fromDate = date;
        $scope.toDate = date;
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

    // $scope.createPayment = function () {
    //     $scope.listBill = [];
    //     let employeeID = $scope.employeeID;
    //     let time = new Date();
    //     let startDate = startDateFormat(time);
    //     let endDate = endtDateFormat(time);
    //     $http.get(`/bachhoa/api/bill/findByEmployeeAndDate/${employeeID}/${startDate}/${endDate}`).then(resp => {
    //         $scope.bills = resp.data;
    //         let money = 0;
    //         angular.forEach($scope.bills, function (item) {
    //             item.timeCreate = dateFormat(item.timeCreate);
    // 			let data = {
    // 				bill: item,
    // 				amountReceivable: Math.round((item.totalAmount - item.reduced) / 1000) * 1000
    // 			}
    // 			$scope.listBill.push(data);
    //             money += data.amountReceivable;
    //         })
    //         $scope.payment = {
    //             employeeID: employeeID,
    //             adminID: $scope.employee.employeeID,
    //             totalAmount: money,
    //             timePay: new Date().getTime(),
    //             paied: false
    //         }
    //         $http.post(`/bachhoa/api/paymentHistory/create`, $scope.payment).then(resp =>{
    //             $scope.payment = resp.data;
    //         })
    //     })


    // }

    $scope.pay = function (id) {
        $http.get(`/bachhoa/api/paymentHistory/findByID/${id}`).then(resp => {
            let item = resp.data;
            item.timeReceived = new Date().getTime();
            item.admin = $scope.employee;
            item.paied = true;
            $http.put(`/bachhoa/api/paymentHistory/update`, item).then(() => {
                let index = $scope.items.findIndex(item => item.id == id);
                $scope.items.splice(index, 1, item);
                loadHistory();
                alert('Đã thu');
            })
        }).catch(error => {
            console.log(error);
        });
    };

    $scope.findEmployee = function (email) {
        $http.get(`/bachhoa/api/employee/findByEmail/${email}`).then(resp => {
            $scope.employee = resp.data;
        });
    }

    // gợi ý nhân viên
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

        // Lấy danh dách các nhân viên
        initAutoComplete() {
            $http.get(`/bachhoa/api/employees/getAll`).then(resp => {
                $scope.listEmployee = resp.data;
                console.log($scope.listEmployee);
                let list = [];
                angular.forEach($scope.listEmployee, function (item) {
                    let employee = item.employeeID + " - " + item.employeeName;
                    list.push(employee);
                })
                console.log(list);
                $scope.autocompleteInput.autocomplete(document.getElementById("employee"), list);
            }).catch(error => {
                console.log('Error', error)
            });
        }
    }
    //------------------------------------------------//

    //--------------------------------------------//

    // Tìm nhân viên theo email
    //let email = document.getElementById('email').innerText;
    $scope.findEmployee("dongnghiepit@gmail.com")

    SetDefaultDate();
    loadHistory();

    // Khởi chạy lấy mảng gợi ý nhân viên
    $scope.autocompleteInput.initAutoComplete();

})