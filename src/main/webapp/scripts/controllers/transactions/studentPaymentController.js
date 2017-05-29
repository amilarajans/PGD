/**
 * Created by Manoj Janaka on 14-11-2016.
 */
activitiAdminApp.controller('StudentPaymentController', ['$rootScope', '$scope', '$http', 'toastr','$uibModal',
    function ($rootScope, $scope, $http, toastr,$uibModal) {

        $rootScope.navigation = {selection: 'studentPayments'};

        $scope.paymentList = [];
        $scope.department = {};
        $scope.maxSize = 10;
        $scope.itemsPerPage = 0;
        $scope.totalItems = 0;
        $scope.currentPage = 1;
        $scope.selectedStudent = {};

        $scope.editMode = false;

        $http.get('app/api/v1/student/allStudent').success(function (rs) {
            $scope.studentList = rs;
        }).error(function (e) {
            $scope.studentList = [];
        });

        $scope.pageChanged = function () {
            $scope.paymentList = [];
            if (!$scope.departmentName) {
                name = '*';
            } else {
                name = $scope.departmentName;
            }
            $http.get('app/api/v1/coursePayments/all', {
                params: {
                    page: $scope.currentPage,
                    name: name
                }
            }).success(function (rs) {
                $scope.paymentList = rs;
            }).error(function (e) {
                $scope.paymentList = [];
                console.log(e);
            });
        };

        $scope.makePayment = function (element) {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'views/pages/transactions/payment.html',
                controller: 'paymentController',
                size: 'lg',
                resolve: {
                    element: function () {
                        return element;
                    },
                    selectedStudent: function () {
                        return $scope.selectedStudent;
                    }
                }
            });

            modalInstance.result.then(function (payment) {

                $http.post('app/api/v1/department/update', $scope.department).success(function (data) {
                    toastr.success('Successfully Updated !!');
                    $scope.pageChanged();
                    $scope.reset();
                }).error(function (data) {
                    toastr.error(data.message);
                });
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        $scope.add = function () {
            $http.post('app/api/v1/department/save', $scope.department).success(function (data) {
                toastr.success('Successfully Saved !!');
                $scope.pageChanged();
                $scope.reset();
            }).error(function (data) {
                toastr.error('Failed to Save !!');
            });
        };

        $scope.update = function () {
            $http.post('app/api/v1/department/update', $scope.department).success(function (data) {
                toastr.success('Successfully Updated !!');
                $scope.pageChanged();
                $scope.reset();
            }).error(function (data) {
                toastr.error(data.message);
            });
        };

        $scope.edit = function (department) {
            $scope.department = department;
            $scope.editMode = true;
        };

        $scope.delete = function (id) {
            $http.delete('app/api/v1/department/delete/' + id).success(function (data) {
                toastr.success('Successfully Deleted !!');
                $scope.pageChanged();
            }).error(function (data) {
                toastr.error(data.message);
            });
        };

        $scope.reset = function () {
            $scope.department = {};
            $scope.editMode = false;
        };

        $scope.search = function () {
            $scope.pageChanged();
        };

        // $scope.pageChanged();
    }]);