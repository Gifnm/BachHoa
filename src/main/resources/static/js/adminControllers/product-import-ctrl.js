const host = "/bachhoa/api";

const app = angular.module("app", []);
app.controller("ctrl", function ($scope, $http, $filter) {
    $scope.menu = 'nhaphang'
    $scope.deliveryNotes = []
    $scope.currentDeliveryNote = {};
    $scope.successMessage = '';
    $scope.errorMessage = '';
    $scope.pageSize = 5;
    $scope.currentFeature = 'create';
    $scope.statusFilters = {
        waiting: false,
        checking: false,
        completed: false,
        waitingCount: 0,
        checkingCount: 0,
        completedCount: 0
    };
    $scope.inventory_filter = 0;
    $scope.min_inventory = 0;
    $scope.max_inventory = 0;


    $scope.account = {} //Account of login employee
    $scope.getAccount = function () {
        let email = document.getElementById('accountEmail').innerText;
        return $http.get(`${host}/employee/findByEmail/${email}`).then(resp => {
            $scope.account = resp.data;
        }).catch(error => {
            console.log("[Product-image-ctrl:getAccount():25]\n> Error: " + error);
        });
    }


    //call function get account and init data
    $scope.getAccount().then(() => {
        let productUrl = `${host}/products/` + $scope.account.store.storeID;
        $http.get(productUrl).then(resp => {
            $scope.products = resp.data;
            $scope.min_inventory = $scope.getMinInventory();
            console.log($scope.min_inventory);
            $scope.max_inventory = $scope.getMaxInventory();
            console.log($scope.max_inventory);
        });
        $scope.getDeliveryNotes();
        $scope.showRequest();
    });

    // Function to update the selected property in the products array
    function updateSelectedStatus(product, isSelected) {
        const index = $scope.products.findIndex(p => p.productID === product.productID);
        if (index !== -1) {
            $scope.products[index].selected = isSelected;
        }
    }
    // Function to calculate the total amount for selected products
    $scope.calculateTotalAmount = function () {
        let totalAmount = 0;

        for (const product of $scope.selectedProducts) {
            totalAmount += product.importPrice * product.quantity;
        }

        return totalAmount;
    };

    $scope.selectedProducts = [];
    // Function to add a product to the selected list
    $scope.addProduct = function (product) {
        // Check if the product is already in selectedProducts based on productID
        let isProductInList = $scope.selectedProducts.some(function (element) {
            return product.productID === element.productID;
        });
        product.selected = true;
        updateSelectedStatus(product, true);

        // If the product is not in the list, add it
        if (!isProductInList) {
            let selectedProduct = {
                productID: product.productID,
                productName: product.productName,
                importPrice: product.importPrice,
                quantity: 1, // You can set the initial quantity as needed
                index: $scope.selectedProducts.length + 1 // Incremental index
            };
            $scope.selectedProducts.push(selectedProduct);
        }
        console.log($scope.selectedProducts);
    };

    // Function to remove a product from the selected list
    $scope.removeProduct = function (index) {
        const removedProduct = $scope.selectedProducts.splice(index, 1)[0];
        if (removedProduct) {
            updateSelectedStatus(removedProduct, false);
        }
    };

    // ... existing code ...

    // Function to return the minimum and maximum inventory
    $scope.getMinInventory = function () {
        let minInventory = $scope.products[0].inventory;
        for (const product of $scope.products) {
            if (product.inventory < minInventory) {
                minInventory = product.inventory;
            }
            return minInventory;
        }
    }
    $scope.getMaxInventory = function () {
        let maxInventory = 0;
        for (const product of $scope.products) {
            if (product.inventory > maxInventory) {
                maxInventory = product.inventory;
            }
        }
        return maxInventory;
    }

    // ... existing code ...

    // Function filter product by inventory and keyword
    $scope.filterProducts = function (inventory_filter, searchProduct) {
        let query = searchProduct || '';
        $scope.filteredProducts = $scope.products.filter(function (product) {
            return product.inventory <= inventory_filter && product.productName.toLowerCase().includes(query.toLowerCase());
        });
    };


    // Function to create the DeliveryNote with selected products
    $scope.createDeliveryNote = function () {
        // Prepare the data for the API request
        let selectedProductsData = [];
        $scope.selectedProducts.forEach(function (product) {
            let productData = {
                productID: product.productID,
                quantity: product.quantity || 0, // Use 0 if quantity is not provided
                index: product.index
            };
            selectedProductsData.push(productData);
        });
        console.log('selectProductData: ' + selectedProductsData);
        let deliveryNoteDTO = {
            storeID: $scope.account.store.storeID,
            employeeID: $scope.account.employeeID,
            detailedDeliveryNotes: selectedProductsData
        };

        // Make the API request
        $http.post(`${host}/deliverynotapi/create`, deliveryNoteDTO)
            .then(function (response) {
                // Handle success, e.g., show a success message
                console.log('Delivery note created successfully:', response.data);
                Swal.fire({
                    title: 'Thành công!',
                    text: 'Tạo phiếu nhập mới thành công.',
                    icon: 'success',
                    showConfirmButton: false,
                    timer: 1500
                });
            })
            .catch(function (error) {
                // Handle error, e.g., show an error message
                console.error('Error creating delivery note:', error.data);
                Swal.fire({
                    title: 'Thất bại!',
                    text: 'Tạo mới phiếu nhập thất bại. Vui lòng thử lại.',
                    icon: 'error',
                    showConfirmButton: false,
                    timer: 1500
                });
            });
        // Clear the selected products list
        $scope.selectedProducts = [];
        // Reset status of list product
        $scope.products.forEach((product) => {
            product.selected = false;
        })
        //Reload the list delivery note
        $scope.getDeliveryNotes();
        $scope.currentPage = 0;
        $scope.changeView('listView', '');
    };

    $scope.updateDetailDeliveryNote = function () {
        // Prepare the data for the API request
        let selectedProductsData = [];
        $scope.selectedProducts.forEach(function (product) {
            let productData = {
                productID: product.productID,
                quantity: product.quantity || 0, // Use 0 if quantity is not provided
                index: product.index
            };
            selectedProductsData.push(productData);
        });
        // Make the API request
        $http.put(`${host}/deliverynotapi/` + $scope.currentDeliveryNote.id, selectedProductsData)
            .then(function (response) {
                // Handle success, e.g., show a success message
                console.log('Delivery note updated successfully:', response.data);
                Swal.fire({
                    title: 'Thành công!',
                    text: 'Cập nhật thành công',
                    icon: 'success',
                    showConfirmButton: false,
                    timer: 1500
                });
                console.log('notification:');

                // Clear the selected products list
                $scope.selectedProducts = [];
                // Reset status of list product
                $scope.products.forEach((product) => {
                    product.selected = false;
                })
                //Reload the list delivery note
                $scope.getDeliveryNotes();
                $scope.currentPage = 0;
                $scope.changeView('listView', '');
            })
            .catch(function (error) {
                // Handle error, e.g., show an error message
                console.error('Error updating delivery note:', error.data);
                // Show a success notification
                Swal.fire({
                    title: 'Lỗi!',
                    text: 'Cập nhật phiếu nhập thất bại. Vui lòng thử lại.',
                    icon: 'error',
                    showConfirmButton: false,
                    timer: 1500
                });
            });
    }

    $scope.deleteDeliveryNote = function (deliveryNote) {
        // Create a confirmation message
        const confirmationMessage = `Bạn có chắc muốn xóa phiếu nhập ${deliveryNote.id}?`;

        // Show a confirmation modal using Bootstrap SweetAlert2
        Swal.fire({
            title: 'Xóa phiếu nhập',
            text: confirmationMessage,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085ed',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Xác nhận xóa!',
            cancelButtonText: 'Hủy'
        }).then((result) => {
            if (result.value) {
                // Call the API to delete the delivery note
                $http.delete(`${host}/deliverynotapi/${deliveryNote.id}`)
                    .then(() => {
                        // Show a success notification
                        Swal.fire({
                            title: 'Success!',
                            text: 'Delivery note has been deleted.',
                            icon: 'success',
                            showConfirmButton: false,
                            timer: 1500
                        });

                        // Reload the list of delivery notes to reflect the changes
                        $scope.getDeliveryNotes();
                    })
                    .catch((error) => {
                        // Show an error notification
                        console.error(error);
                        Swal.fire({
                            title: 'Error!',
                            text: 'Failed to delete delivery note. Please try again.',
                            icon: 'error',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    });
            }
        });
    };

    // Function to fetch delivery notes
    $scope.getDeliveryNotes = function () {
        // Make a GET request to the API
        $http.get(`${host}/deliverynotapi/with-total-amount/` + $scope.account.store.storeID)
            .then(resp => {
                $scope.deliveryNotes = resp.data;

                // Pagination variables
                $scope.filterTableData();
                $scope.getCurrentPageData();
                $scope.countDataByStatus();
                // Call the function to set default values when the controller is loaded
                $scope.setDurationFilterDefault();
            })
            .catch(error => {
                console.error('Error fetching delivery notes:', error);
            });
    };

    $scope.getDetailDeliveryNote = function (deliveryNote) {
        console.log(`get detail with id ${deliveryNote.id}`)
        $scope.detailDeliveries = [];
        if (deliveryNote.status === 'Hoàn tất') {
            console.log('getting purchase history...');
            $http.get(`${host}/purchasehistory/${deliveryNote.id}`).then(resp => {
                console.log('get success')
                let purchaseHistories = resp.data;
                let index = 0;
                purchaseHistories.forEach((row) => {
                    let detailDeliveryNote = {};
                    detailDeliveryNote.product = {};
                    detailDeliveryNote.index = index += 1;
                    detailDeliveryNote.productID = row.productID
                    detailDeliveryNote.product = row.product
                    detailDeliveryNote.quantity = row.quantityReceived;
                    detailDeliveryNote.count = row.confirmedQuantity;
                    detailDeliveryNote.product.importPrice = row.totalAmount / row.confirmedQuantity;
                    console.log('after map: ' + detailDeliveryNote.product.importPrice + row.totalAmount);
                    $scope.detailDeliveries.push(detailDeliveryNote);
                })
                $scope.currentDeliveryNote = deliveryNote;
            }).catch(error => {
                console.error('Error fetching purchase histories:', error)
            });
        } else {
            console.log('getting detail delivery note...');
            $http.get(`${host}/detaildeliverynote/getall/${deliveryNote.id}`).then(resp => {
                $scope.detailDeliveries = resp.data;
                $scope.currentDeliveryNote = deliveryNote;
            }).catch(error => {
                console.error('Error fetching detail delivery notes:', error);
            });
        }
    };

    $scope.editDeliveryNote = function (deliveryNote) {
        // Reset status of list product
        // $scope.products.forEach((product) => {
        //     product.selected = false;
        // })
        $http.get(`${host}/detaildeliverynote/getall/${deliveryNote.id}`).then(resp => {
            $scope.detailDeliveries = resp.data;
            $scope.currentDeliveryNote = deliveryNote;
            console.log($scope.detailDeliveries);
            // Get list detailDeliveryNote
            $scope.selectedProducts = [];
            $scope.detailDeliveries.forEach(row => {
                let selectedProduct = {
                    productID: row.productID,
                    productName: row.product.productName,
                    importPrice: row.product.importPrice,
                    quantity: row.quantity,
                    index: row.index
                };
                $scope.selectedProducts.push(selectedProduct);
                updateSelectedStatus(row.product, true);
            })
            $scope.changeView('createView', 'updateFeature');
        });
    }

    // Function to export the table detailDeliveryNote data and modal header to Excel
    $scope.detailDeliveryNoteDataToExport = function () {
        let data = [];

        try {
            console.log('Đang lấy data...');

            // Add header information
            data.push(['Mã phiếu', $scope.currentDeliveryNote.id]);
            data.push(['Người lập phiếu', $scope.currentDeliveryNote.employeeName]);
            data.push(['Ngày lập', $filter('date')($scope.currentDeliveryNote.timeCreate, 'dd/MM/yyyy')]);
            data.push(['Ngày nhập', $filter('date')($scope.currentDeliveryNote.timeCompleted, 'dd/MM/yyyy')]);
            data.push(['Trạng thái', $scope.currentDeliveryNote.timeCompleted == null ? 'Đang nhập' : 'Đã hoàn thành']);

            // Add table content
            data.push(['STT', 'Tên Sản phẩm', 'Mã số', 'Số lượng nhập', 'Thực nhập', 'Đơn giá nhập', 'Thành tiền']);
            angular.forEach($scope.detailDeliveries, function (row) {
                data.push([
                    row.index,
                    row.product.productName,
                    row.product.productID,
                    row.quantity,
                    row.count,
                    $filter('currency')(row.product.importPrice, '₫', '0'),
                    $filter('currency')(row.product.importPrice * row.quantity, '₫', '0')
                ]);
            });

            // Add footer information
            data.push(['Tổng cộng', '', '', '', '', '', $filter('currency')($scope.currentDeliveryNote.totalAmount, '₫', '0')]);

            console.log('Đã lấy data:', data);

            // Call the export function with the retrieved data
            $scope.exportToExcel(data, 'Phiếu nhập hàng chi tiết', `PhieuNhapHang_${$scope.currentDeliveryNote.timeCreate}`);
        } catch (error) {
            console.error('An error occurred:', error);
        }
    };
    // Function to export data to Excel
    $scope.exportToExcel = function (data, sheetName, fileName) {
        console.log('Đang xuất...')
        // Create a new workbook
        let wb = XLSX.utils.book_new();
        // Add a worksheet to the workbook
        let ws = XLSX.utils.aoa_to_sheet(data);
        // Assign the worksheet to the workbook
        XLSX.utils.book_append_sheet(wb, ws, sheetName);

        // Save the workbook as an Excel file
        XLSX.writeFile(wb, `${fileName}.xlsx`);
        console.log('Đã xuất.')
    };

    // Pagination
    $scope.setPage = function (page) {
        $scope.currentPage = page;
    }

    $scope.limitPage = function (list) {
        // Pagination variables
        $scope.currentPage = 1;
        $scope.totalPages = Math.ceil(list.length / $scope.pageSize);
        $scope.range = [...Array($scope.totalPages).keys()].map(index => index + 1);
    }

    // Get the current page data
    $scope.getCurrentPageData = function () {
        const startIndex = ($scope.currentPage - 1) * $scope.pageSize;
        const endIndex = startIndex + $scope.pageSize;
        return $scope.filteredDeliveryNotes.slice(startIndex, endIndex);
    };


    $scope.filterTableData = function () {
        const filteredData = $scope.deliveryNotes.filter(row => {
            if ($scope.statusFilters.waiting && row.status === 'Chờ nhập') {
                return true;
            }

            if ($scope.statusFilters.checking && row.status === 'Đang kiểm') {
                return true;
            }

            if ($scope.statusFilters.completed && row.status === 'Hoàn tất') {
                return true;
            }

            if (!$scope.statusFilters.waiting && !$scope.statusFilters.checking && !$scope.statusFilters.completed) {
                return true;
            }

            return false;
        });
        $scope.limitPage(filteredData);
        $scope.filteredDeliveryNotes = filteredData;
    }

    // Function to set the default values for start and end dates
    $scope.setDurationFilterDefault = function () {
        const firstItem = $scope.deliveryNotes[0];
        const lastItem = $scope.deliveryNotes[$scope.deliveryNotes.length - 1];
        $scope.startDateSelected = lastItem.timeCreate ? $filter('date')(lastItem.timeCreate, 'yyyy-MM-dd') : '';
        $scope.endDateSelected = firstItem.timeCreate ? $filter('date')(firstItem.timeCreate, 'yyyy-MM-dd') : '';

        // Call the filter function to update the view
        $scope.filterTableDataByDate();
    }



    // Function to filter the table data based on date range
    $scope.filterTableDataByDate = function () {
        // Parse the selected dates into JavaScript Date objects
        const startDate = new Date($scope.startDateSelected);
        const endDate = new Date($scope.endDateSelected);

        // Filter the delivery notes based on the date range
        $scope.filteredDeliveryNotes = $scope.deliveryNotes.filter(function (row) {
            const rowDate = new Date(row.timeCreate);

            // Check if the row date is within the selected date range
            return rowDate >= startDate && rowDate <= endDate;
        });
    };

    $scope.countDataByStatus = function () {
        $scope.statusFilters.waitingCount = 0;
        $scope.statusFilters.checkingCount = 0;
        $scope.statusFilters.completedCount = 0;
        $scope.deliveryNotes.filter(row => {
            if (row.status === 'Chờ nhập') {
                $scope.statusFilters.waitingCount += 1;
            } else if (row.status === 'Đang kiểm') {
                $scope.statusFilters.checkingCount += 1;
            } else {
                $scope.statusFilters.completedCount += 1;
            }
        });
    }


    $scope.currentView = 'listView';

    $scope.changeView = function (viewName, featureName) {
        $scope.currentView = viewName;
        $scope.currentFeature = featureName;
        // Clear the modify view before close
        if (viewName === 'listView') {
            // Clear the selected products list
            $scope.selectedProducts = [];
            // Reset status of list product
            $scope.products.forEach((product) => {
                product.selected = false;
            })
        }
    };

    $scope.selectAll = false;

    $scope.toggleSelectAll = function () {
        angular.forEach($scope.items, function (item) {
            item.selected = $scope.selectAll;
        });
    };

    $scope.toggleProductSelection = function (product) {
        // Your individual product selection logic here
    };

    $scope.addAll = function () {
        $scope.filteredProducts.forEach(function (product) {
            $scope.addProduct(product);
        });
    };

    //Start: Employee request
    $scope.showRequest = function () {
        $http.get(`/bachhoa/api/employee/Request/${$scope.account.store.storeID}`).then(resp => {
            console.log(`/bachhoa/api/employee/Request/${$scope.account.store.storeID}`)
            $scope.emRequest = resp.data;
            $scope.badge = $scope.emRequest.length;

        })
    };
    $scope.Denied = function (id) {
        $http.put(`/bachhoa/api/employeeDel/${id}`).then(resp => {
            toastMixin.fire({
                title: 'Đã từ chối nhân viên.',
                icon: 'success'
            })
            $scope.showRequest();
        })

    }

    $scope.acceptNV = function (id) {
        $http.put(`/bachhoa/api/employeeAccept/${id}`).then(resp => {
            toastMixin.fire({
                title: 'Nhân viên đã được chấp nhận!',
                icon: 'success'
            })
            $scope.showRequest();
        })

    }
    //End: Employee request
});