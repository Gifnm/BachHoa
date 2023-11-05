var app = angular.module("app", ["ui.bootstrap", "ui.tab.scroll"]);

app.controller("ctrl", function ($scope) {
  $scope.hoadons = [{ id: 1, name: "Hóa đơn 1", active: true }];

  var id = 1;

  var addNewBill = function () {
    if ($scope.hoadons.length == 0) {
      // var id = $scope.hoadons.length + 1;
      id = 1;
      $scope.hoadons.push({
        id: id,
        name: "Hóa đơn " + id,
        active: true,
      });
    } else {
      id = id + 1;
      var id_next = id;
      $scope.hoadons.unshift({
        id: id_next,
        name: "Hóa đơn " + id_next,
        active: true,
      });
    }
  };

  var setAllInactive = function () {
    angular.forEach($scope.hoadons, function (bill) {
      bill.active = false;
    });
  };

  $scope.addBill = function () {
	console.log("alo");
    setAllInactive();
    addNewBill();
  };

  $scope.removeTab = function (index) {
    $scope.hoadons.splice(index, 1);
    if ($scope.hoadons.length == 0) {
      addNewBill();
    }
  };
});

// app.controller("TabsChildController", function ($scope, $log) {});
