let current_host = window.location.host;
// Của trang đăng nhập
$(document).ready(function () {
  $(".message a").click(function () {
    $(".form").animate(
      {
        height: "toggle",
        padding: "toggle",
        margin: "toggle",
        opacity: "toggle",
      },
      "slow"
    );
    $(".QMK-form").animate(
      {
        height: "toggle",
        padding: "toggle",
        margin: "toggle",
        opacity: "toggle",
      },
      "slow"
    );
  });
  $(".message-2 a").click(function () {
    $(".form").animate(
      {
        height: "toggle",
        padding: "toggle",
        margin: "toggle",
        opacity: "toggle",
      },
      "slow"
    );
    $(".logo").animate({
      width: "toggle",
      left: "toggle",
    });
    $(".DK-form").animate(
      {
        height: "toggle",
        width: "toggle",
        padding: "toggle",
        opacity: "toggle",
        margin: "toggle",
      },
      "slow"
    );
  });
});

// Dành cho trang Đăng lý Role
// Form main
$(document).ready(function () {
  $(".QL label input").click(function () {
    $(".form").animate(
      {
        opacity: "toggle",
      },
      "linear"
    );
    $(".QL-form").animate(
      {
        right: 0,
        padding: "toggle",
        opacity: "toggle",
      },
      "slow"
    );
  });
  $(".NV label input").click(function () {
    $(".form").animate(
      {
        opacity: "toggle",
      },
      "slow"
    );
    $(".NV-form").animate(
      {
        left: 0,
        padding: "toggle",
        opacity: "toggle",
      },
      "slow"
    );
  });
});

// Hai form phụ
$(document).ready(function () {
  $(".back-left a").click(function () {
    $(".form").animate(
      {
        opacity: "toggle",
      },
      "slow"
    );
    $(".NV-form").animate(
      {
        left: 999,
        padding: "toggle",
        opacity: "toggle",
      },
      "slow"
    );
  });
  $(".back-right a").click(function () {
    $(".form").animate(
      {
        opacity: "toggle",
      },
      "slow"
    );
    $(".QL-form").animate(
      {
        right: 999,
        padding: "toggle",
        opacity: "toggle",
      },
      "slow"
    );
  });
});

// Form đăng ký tài khoản
const app = angular.module("register-app", []);
app.controller("register-ctrl", function ($scope, $http) {
  $scope.items = [];
  $scope.form = {};
  $scope.employee = {};

  function isEmail(value) {
    var regExp = /^[A-Za-z][\w$.]+@[\w]+\.\w+$/;
    return regExp.test(value)
  }

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

  $scope.getAllUsers = function () {
    // load accounts
    $http.get("/bachhoa/api/employees/getAll").then((resp) => {
      $scope.items = resp.data;
    });
  };

  // Thêm tài khoản mới
  $scope.create = function () {
    var item = angular.copy($scope.form);
    item.storeID = "";
    item.roleID = "";
    item.firstWork = new Date();
    item.active = 0;
    if (item.employeeName == "" || item.employeeName == null) {
      Swal.fire({
        title: "Hãy điền họ và tên của bạn !",
        icon: "warning",
        timer: 2000,
        timerProgressBar: true,
        showConfirmButton: false,
      });
      $event.preventDefault();
    } else if (item.email == "" || item.email == null) {
      // Bắt lỗi rỗng
      Swal.fire({
        title: "Đề nghị nhập Email của bạn !",
        icon: "warning",
        timer: 2000,
        timerProgressBar: true,
        showConfirmButton: false,
      });
      $event.preventDefault();
    } else if (!isEmail(item.email)) {
      // Bắt lỗi không đúng định dạng
      Swal.fire({
        title: "Email không đúng !",
        text: "Bạn đang nhập sai định email, hãy kiểm tra lại !",
        icon: "warning",
        timer: 2000,
        timerProgressBar: true,
        showConfirmButton: false,
      });
      $event.preventDefault();
    }
    else if ($scope.items.find((p) => p.email == item.email)) {
      Swal.fire({
        title: "Tài khoản này đã tồn tại.",
        icon: "warning",
        timer: 2000,
        timerProgressBar: true,
        showConfirmButton: false,
      });
      $event.preventDefault();
    } else if (item.password != document.getElementById("confirmPassword").value) {
      // console.log(item.password +" , "+ document.getElementById("confirmPassword").value);
      Swal.fire({
        title: "Mật khẩu không trùng khớp nhau, hãy kiểm tra lại.",
        icon: "error",
        timer: 2000,
        timerProgressBar: true,
        showConfirmButton: false,
      });
      $event.preventDefault();
    } else {
      $http
        .post(`/bachhoa/api/employee/insert`, item)
        .then((resp) => {
          $scope.employee = resp.data;
          location.href = current_host + "/register";
          // $event.preventDefault();
        })
        .catch((error) => {
          alert("Lỗi đăng ký!");
          console.log("Error", error);
        });
    }
  };

  // khởi động
  $scope.getAllUsers();
});
