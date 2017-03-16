/**
 * Created by Manoj Janaka on 14-11-2016.
 */
activitiAdminApp.controller('PaymentModeController', ['$rootScope', '$scope', '$http', 'toastr',
    function ($rootScope, $scope, $http, toastr) {

        $rootScope.navigation = {selection: 'paymentMode'};

        $scope.paymentList = [];
        $scope.payment = {};
        $scope.maxSize = 10;
        $scope.itemsPerPage = 0;
        $scope.totalItems = 0;
        $scope.currentPage = 1;

        $scope.editMode = false;

        $scope.pageChanged = function () {
            if (!$scope.paymentName) {
                name = '*';
            } else {
                name = $scope.paymentName;
            }
            $http.get('app/api/v1/payment_mode/all', {
                params: {
                    page: $scope.currentPage,
                    name: name
                }
            }).success(function (rs) {
                $scope.paymentList = rs.content;
                $scope.totalItems = rs.totalElements;
                $scope.itemsPerPage = rs.size;
            }).error(function (e) {
                $scope.paymentList = [];
                console.log(e);
            });
        };

        $scope.add = function () {
            $http.post('app/api/v1/payment_mode/save', $scope.payment).success(function (data) {
                toastr.success('Successfully Saved !!');
                $scope.pageChanged();
                $scope.reset();
            }).error(function (data) {
                toastr.error('Failed to Save !!');
            });
        };

        $scope.update = function () {
            $http.post('app/api/v1/payment_mode/update', $scope.payment).success(function (data) {
                toastr.success('Successfully Updated !!');
                $scope.pageChanged();
                $scope.reset();
            }).error(function (data) {
                toastr.error(data.message);
            });
        };

        $scope.edit = function (payment) {
            $scope.payment = payment;
            $scope.editMode = true;
        };

        $scope.delete = function (id) {
            $http.delete('app/api/v1/payment_mode/delete/' + id).success(function (data) {
                toastr.success('Successfully Deleted !!');
                $scope.pageChanged();
            }).error(function (data) {
                toastr.error(data.message);
            });
        };

        $scope.reset = function () {
            $scope.payment = {};
            $scope.editMode = false;
        };

        $scope.search = function () {
            $scope.pageChanged();
        };

        $scope.pageChanged();
    }]);