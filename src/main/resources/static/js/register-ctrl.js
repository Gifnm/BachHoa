let current_host = window.location.host;
app.controller("register-ctrl", function ($scope, $http) {
  // Stores
  $scope.stores = [];
  $scope.sessionStore = {};
  $scope.storeToJoin = {};
  $scope.investmentCosts = ['500 triệu', '1 tỷ', '5 tỷ'];
  // Employees
  $scope.info = {};
  $scope.employees = [];
  $scope.employee = {};
  $scope.employeeInfo = {};
  // Roles
  $scope.authority = [];
  $scope.authorities = {};

  // SweetAler 2
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

  $scope.getAllStores = function () {
    console.log(current_host);
    // load stores
    $http.get("/bachhoa/api/stores").then((resp) => {
      $scope.stores = resp.data;
    });
  };

  $scope.getAllUsers = function () {
    // load accounts
    $http.get("/bachhoa/api/employees/getAll").then((resp) => {
      $scope.employees = resp.data;
    });
  };

  $scope.getInfo = function () {
    var employeeID = document.getElementById("employeeID").innerText;
    $http.get(`/bachhoa/api/employee/findByID/${employeeID}`).then((resp) => {
      $scope.employeeInfo = resp.data;
      console.log($scope.employeeInfo);
    });
  };

  $scope.getAuth = function () {
    $http.get("/bachhoa/api/authorities").then((resp) => {
      $scope.authority = resp.data;
      console.log($scope.authority);
    });
  };

  // Thêm store mới theo user (lấy id user)
  $scope.createStore = function () {
    var storeN = document.getElementById("storeName").value;
    var storeA = document.getElementById("storeAddress").value;
    var storeS = document.getElementById("storeSize").value;
    if (storeN == "" || storeN == null) {
      toastMixin.fire({
        title: "Hãy đặt tên cửa hàng của bạn !",
        icon: "warning",
      });
    } else if (storeA == "" || storeA == null) {
      toastMixin.fire({
        title: "Chưa nhập địa chỉ cửa hàng, kiểm tra lại !",
        icon: "warning",
      });
    } else if (storeS == "" || storeS == null) {
      toastMixin.fire({
        title: "Quy mô cửa hàng bạn là bao nhiêu ? (500 tỷ, 2 tỷ, 5 tỷ, ...) !",
        icon: "warning",
      });
    } else {
      var storeInfo = angular.copy($scope.info);
      if ($scope.stores.find(p => p.storeName == storeInfo.storeName)) {
        toastMixin.fire({
          title: "Cửa hàng hiện đã có người sở hữu !",
          icon: "error",
        });
        $event.preventDefault();
      } else {
        $http.post(`/bachhoa/api/store/insert`, storeInfo).then(resp => {
          var addStore = resp.data;
          console.log(addStore);
          $scope.stores.push(resp.data);
          // Tìm nhân viên theo ID
          var employeeID = document.getElementById("employeeID").innerText;
          $http.get(`/bachhoa/api/employee/findByID/${employeeID}`).then(responese => {
            $scope.employee = responese.data;
            $scope.authorities = {
              employee: $scope.employee,
              role: { roleID: "qlch", workRole: "Quản lý cửa hàng" }
            };
            if ($scope.authority.find(a => a.employeeID == $scope.employee.employeeID)) {
              console.log("Đã có auth.");
            } else {
              // Thêm auth mới
              $http.post(`/bachhoa/api/employee/insert/authorities`, $scope.authorities).then(() => {
              });
            }
            // Update role lại
            var role = "qlch";
            $http.put(`/bachhoa/api/employee/updateRoles/${role}/${employeeID}`).then(resp => { console.log(resp.data) });
            $scope.employee.store = addStore;
            $scope.employee.active = 1;
            // console.log($scope.employee);
            $http
              .put(
                `/bachhoa/api/employee/update`,
                $scope.employee
              )
              .then(() => {
                Swal.fire({
                  title: "Đăng ký cửa hàng thành công, xin chúc mừng !",
                  html: "Tự động đăng nhau sau <b></b> mili giây.",
                  icon: "success",
                  timer: 3000,
                  timerProgressBar: true,
                  confirmButtonText: "Đăng nhập ngay",
                  didOpen: () => {
                    Swal.showLoading();
                    const timer = Swal.getPopup().querySelector("b");
                    timerInterval = setInterval(() => {
                      timer.textContent = `${Swal.getTimerLeft()}`;
                    }, 100);
                  },
                  willClose: () => {
                    clearInterval(timerInterval);
                  },
                }).then((result) => {
                  if (result.isConfirmed) {
                    location.href = "http://localhost:8081/login";
                  } else if (result.dismiss === Swal.DismissReason.timer) {
                    location.href = "http://localhost:8081/login";
                  }
                });
              })
              .catch((error) => {
                toastMixin.fire({
                  title: "Đã có lỗi xảy ra, hãy kiểm tra lại thông tin.",
                  icon: "error",
                });
                console.log("Error", error);
              });
          });
        }
            // Update role lại
            var role = "qlch";
        $http.put(`/bachhoa/api/employee/updateRoles/${role}/${employeeID}`).then(resp => { console.log(resp.data) });
        $scope.employee.store = addStore;
        $scope.employee.active = 1;
        // console.log($scope.employee);
        $http
          .put(
            `/bachhoa/api/employee/update`,
            $scope.employee
          )
          .then(() => {
            Swal.fire({
              title: "Đăng ký cửa hàng thành công, xin chúc mừng !",
              html: "Tự động đăng nhau sau <b></b> mili giây.",
              icon: "success",
              timer: 3000,
              timerProgressBar: true,
              confirmButtonText: "Đăng nhập ngay",
              didOpen: () => {
                Swal.showLoading();
                const timer = Swal.getPopup().querySelector("b");
                timerInterval = setInterval(() => {
                  timer.textContent = `${Swal.getTimerLeft()}`;
                }, 100);
              },
              willClose: () => {
                clearInterval(timerInterval);
              },
            }).then((result) => {
              if (result.isConfirmed) {
                location.href = "/login";
              } else if (result.dismiss === Swal.DismissReason.timer) {
                location.href = current_host + "/login";

              }
            });
          })
          .catch((error) => {
            toastMixin.fire({
              title: "Đã có lỗi xảy ra, hãy kiểm tra lại thông tin.",
              icon: "error",
            });
            console.log("Error", error);
          });
      });
        })
          .catch ((error) => {
  toastMixin.fire({
    title: "Không thể đăng ký cửa hàng !",
    icon: "error",
  });
  console.log("Error", error);
});
      }
    }
  };

// Xin việc làm nhân viên
$scope.joinStore = function () {
  var storeID = angular.copy($scope.storeToJoin);
  console.log(storeID.storeID);
  if ($scope.stores.find((p) => p.storeID == storeID.storeID)) {
    // Tìm nhân viên theo ID
    var employeeID = document.getElementById("employeeID").innerText;
    $http
      .get(`/bachhoa/api/store/findByID/${storeID.storeID}`)
      .then((resp) => {
        $scope.sessionStore = resp.data;
        console.log($scope.sessionStore);
      });
    $http.get(`/bachhoa/api/employee/findByID/${employeeID}`).then((resp) => {
      $scope.employee = resp.data;
      $scope.authorities = {
        employee: $scope.employee,
        role: { roleID: "bhoa", workRole: "Chưa có cửa hàng" },
      };
      if (
        $scope.authority.find(
          (a) => a.employeeID == $scope.employee.employeeID
        )
      ) {
        console.log("Đã có auth.");
      } else {
        // Thêm auth mới cho account
        $http.post(
          `/bachhoa/api/employee/insert/authorities`,
          $scope.authorities
        );
      }
      // Thêm role nhân viên
      var role = "bhoa";
      $http
        .put(`/bachhoa/api/employee/updateRoles/${role}/${employeeID}`)
        .then(() => { });
      $scope.employee.store = $scope.sessionStore;
      $scope.employee.active = 0;
      console.log($scope.employee);
      $http
        .put(`/bachhoa/api/employee/update`, $scope.employee)
        .then((resp) => {
          $scope.employeeInfo = resp.data;
          // console.log($scope.employeeInfo);
          Swal.fire({
            title: "Gửi đơn xin việc thành công !",
            icon: "success",
            timer: 2000,
            timerProgressBar: true,
            showConfirmButton: false,
          }).then((result) => {
            if (result.dismiss === Swal.DismissReason.timer) {
              location.href = current_host + "/business/load";
            }
          });
        })
        .catch((error) => {
          toastMixin.fire({
            title: "Đã có lỗi xảy ra, kiểm tra lại thông tin !",
            icon: "error",
          });
          console.log("Error", error);
        });
    });
  } else {
    toastMixin.fire({
      title:
        "Không tìm thấy cửa hàng mà bạn muốn tham gia, hãy kiểm tra lại !",
      icon: "warning",
    });
  }
};

$scope.quitJob = function () {
  if (
    $scope.stores.find((p) => p.storeID == $scope.employeeInfo.store.storeID)
  ) {
    swal
      .fire({
        title: "Bạn có chắc muốn hủy không ?",
        text: "Hãy kiểm tra kỹ trước khi lựa chọn !",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Chắc chắn !",
        cancelButtonText: "Suy nghĩ lại !",
        customClass: {
          confirmButton: "btn btn-warning fw-bold rounded-5 cursor shadow",
          cancelButton:
            "btn btn-outline-success rounded-5 cursor ms-5 shadow",
        },
        buttonsStyling: false,
      })
      .then((result) => {
        if (result.isConfirmed) {
          // Tìm nhân viên theo ID
          var employeeID = document.getElementById("employeeID").innerText;
          $http
            .get(`/bachhoa/api/employee/findByID/${employeeID}`)
            .then((resp) => {
              $scope.employee = resp.data;
              if (
                $scope.authority.find(
                  (a) => a.employeeID == $scope.employee.employeeID
                )
              ) {
                $http
                  .delete(
                    `/bachhoa/api/authorities/deleteBy2ID/${$scope.employee.role.roleID}/${employeeID}`
                  )
                  .then(() => {
                    // ...
                  })
                  .catch((error) => {
                    alert("Thu hồi quyền sử dụng thất bại");
                    console.log("Eror", error);
                  });
              }
              $scope.employee.roles = null;
              $scope.employee.store = null;
              $scope.employee.active = 0;

              console.log($scope.employee);
              $http
                .put(
                  `/bachhoa/api/employee/update`,
                  $scope.employee
                )
                .then(() => {
                  Swal.fire({
                    title: "Hủy đơn thành công !",
                    icon: "success",
                    timer: 1500,
                    timerProgressBar: true,
                    showConfirmButton: false,
                  }).then((result) => {
                    if (result.dismiss === Swal.DismissReason.timer) {
                      location.href = current_host + "/business/load";
                    }
                  });
                })
                .catch((error) => {
                  toastMixin.fire({
                    title: "Đã xảy ra lỗi, hãy kiểm tra lại thông tin !",
                    icon: "warning",
                  });
                  console.log("Error", error);
                });
            });
        }
      });
  } else {
    toastMixin.fire({
      title:
        "Không tìm thấy cửa hàng mà bạn muốn tham gia, hãy kiểm tra lại !",
      icon: "question",
    });
  }
};

// khởi động
$scope.getAllStores();
$scope.getAllUsers();
$scope.getInfo();
});
