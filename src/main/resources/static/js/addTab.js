var app = angular.module("app", ["ui.bootstrap", "ui.tab.scroll"]);

app.controller("add-tab-ctrl", function ($scope) {
  $scope.invoices = [{ id: 1, name: "Hóa đơn 1", active: true }];

  var id = 1;

  var addNewBill = function () {
    alert("run1")
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


