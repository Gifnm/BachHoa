let host = "http://localhost:8081/bachhoa/api";
const app = angular.module("app", []);
app.controller("ctrl", function($scope, $http, $filter){
    $scope.items = [];
    $scope.form = {};
    $scope.categories = []
    $scope.account = {}
    //Mock store
    $scope.store = {
        storeID : 1,
        address : "Trung tâm BachHoa",
        size : "3t"
    }

    $scope.initialize = function(){
        //load products
        var url = `${host}/products`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.nearestExpDate = new Date(item.nearestExpDate)
            })
        });
        //load categories
        url = `${host}/categories`;
        $http.get(url).then(resp =>{
            $scope.categories = resp.data;
            // $http.post('')
        });
        $scope.form.store = $scope.store;
        $scope.form.image = 'image_upload_icon.png';
    }

    $scope.create = function(){
        var item = angular.copy($scope.form);
        var url = `${host}/products`;
        $http.post(url, item).then(resp => {
            resp.data.nearestExpDate = new Date(resp.data.nearestExpDate)
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Them moi thanh cong!");
        }).catch(error => {
            alert("Loi them moi san pham!");
            console.log("Error: "+ error);
        });
    }

    $scope.update = function(){
        var item = angular.copy($scope.form);
        var url = `${host}/products/${item.productID}`;
        $http.put(url, item).then(resp => {
            var index = $scope.items.findIndex(p => p.productID == item.productID);
            $scope.items[index] = item;
            $scope.initialize();
            alert("Cap nhat thanh cong!");
        }).catch(error => {
            alert("Loi cap nhat san pham!");
            console.log("Error", error);
        })
    }

    $scope.delete = function (id) {
        if (confirm("THIS ACTION CAN'T UNDO!\nAre you sure to delete this product?") == true) {
            var url = `${host}/products/${id}`;
            $http.delete(url).then(resp => {
                var index = $scope.items.findIndex(item => item.productID == $scope.form.productID);
                $scope.items.splice(index, 1);
                $scope.reset();
                $scope.initialize();
                alert("Delete successfully!");
            }).catch(error => {
                console.log("Error", error)
            });
        }
    }

    $scope.reset = function(){
        $scope.form = {
            nearestExpDate: new Date(),
            image: 'image_upload_icon.png',
            status:true,
            store: $scope.store
        }
    }

    $scope.edit = function(item){
        $scope.form = angular.copy(item);
        if($scope.form.image == null){
            $scope.form.image = 'image_upload_icon.png';
        }
    }

    $scope.imageChanged = function(files){
        var data = new FormData();
        data.append('file', files[0]);
        var url = `${host}/upload/images`;
        $http.post(url, data, {
            transformRequest: angular.identify,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            alert(resp.data.path)
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Loi upload anh");
            console.log("Error: ", error);
        })
    }
    
    $scope.currentPage = 0;
    $scope.pageSize = 20;
    $scope.sortingOrder = sortingOrder;
    $scope.reverse = false;

    $scope.numberOfPages = function () {
        return Math.ceil($scope.items.length / $scope.pageSize);
    }
    $scope.range = function (start, end) {
        var ret = [];
        if (!end) {
            end = start;
            start = 0;
        }
        for (var i = start; i < end; i++) {
            ret.push(i);
        }
        return ret;
    };

    // calculate page in place
    $scope.groupToPages = function () {
        $scope.pagedItems = [];

        for (var i = 0; i < $scope.items.length; i++) {
            if (i % $scope.pageSize === 0) {
                $scope.pagedItems[Math.floor(i / $scope.pageSize)] = [ $scope.items[i] ];
            } else {
                $scope.pagedItems[Math.floor(i / $scope.pageSize)].push($scope.items[i]);
            }
        }
    };

    // init the filtered items
    $scope.search = function (query) {
        var keyword = encodeURI(query)
        console.log(keyword)
        var url = `${host}/products/search?q=${keyword}`;
        console.log(url)
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.nearestExpDate = new Date(item.nearestExpDate)
            })
        })
        $scope.currentPage = 0;
        $scope.groupToPages();
    };

    $scope.setPage = function(page){
        $scope.currentPage = page;
    }

    $scope.initialize()
    $scope.search("");
});
// ctrl.$inject = ['$scope', '$filter'];
//We already have a limitTo filter built-in to angular,
//let's make a startFrom filter
app.filter('startFrom', function () {
    return function (input, start) {
        start = +start; //parse to int
        return input.slice(start);
    }
});