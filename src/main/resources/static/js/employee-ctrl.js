//const host = "http://localhost:8081/bachhoa/api";
const app = angular.module("app", []);

app.controller("employee-ctrl", function ($scope, $http){
	$scope.employees = [];
	
	
	$scope.initialize = function(){
		$http.get(`/bachhoa/api/employee`).then(resp=>{
			$scope.employees = resp.data;
		})
	
	
 $scope.delete = function (id) {
        if (confirm("Hành động này không thể được hoàn tác!\nCó chắc chắn muốn xóa nhân viên này không?") == true) {
            $http.put(`/bachhoa/api/employeeDel/${id}`).then(resp => {
                let index = $scope.employees.findIndex(employee => employee.employeeID === id);
                $scope.employees.splice(index, 1);
                $scope.initialize();
                alert("employee-ctrl.js:delete():100]\n> Hoàn Thành xóa");
            }).catch(error => {
                console.log("employee-ctrl.js:delete():102]\n> Lỗi", error);
            });
        }
    };
    
	
}
$scope.initialize();
})