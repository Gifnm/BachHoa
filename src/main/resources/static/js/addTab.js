
function xoa() {
    document.getElementById('hoadon1').innerHTML = "";
    document.getElementById('hoadon').innerHTML = "";
}
var app = angular.module("app", []);
app.controller("ctrl", function ($scope) {
    alert("hello")
    $scope.newPanel = function () {
        document.getElementById('myTab').innerHTML
            += '<li class="nav-item" role="presentation" style="position: relative; width: 130px;">'
            + '<a class="nav-link" id="new-tab" data-bs-toggle="tab"'
            + 'data-bs-target="#hoadon" skip-disable role="tab" aria-controls="new-tab" aria-selected="false">Hóa đơn 1'
            + '</a>'
            + '<button type = "button" class="btn-close" aria-label="Close"'
            + 'style="position: absolute; top: 0; right: 0; padding: 8px;"></button>'
            + '</li>';
        document.getElementById('myTabContent').innerHTML
            += '<div class="tab-pane fade table-responsive" id="hoadon" role="tabpanel"'
            + 'aria-labelledby="new-tab">'
            + '<table class="table">'
            + '<thead>'
            + '<tr>'
            + '<th scope="col">Mã sản phẩm</th>'
            + '<th scope="col">Tên sản phẩm</th>'
            + '<th scope="col">Đơn vị</th>'
            + '<th scope="col">Số lượng</th>'
            + '<th scope="col">Đơn giá</th>'
            + '<th scope="col">Thành tiền</th>'
            + '<th scope="col">Bỏ</th>'
            + '</tr>'
            + '</thead>'
            + '<tbody>'
            + '<tr>'
            + '<td>1</td>'
            + '<td>PESSI</td>'
            + '<td>Chai</td>'
            + '<td>5</td>'
            + '<td>20000</td>'
            + '<td>100000</td>'
            + '</tr>'
            + '</tbody>'
            + '</table>'
            + '</div>';
    };
});