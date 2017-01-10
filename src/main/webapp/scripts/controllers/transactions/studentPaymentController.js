/**
 * Created by Manoj Janaka on 14-11-2016.
 */
activitiAdminApp.controller('StudentPaymentController', ['$rootScope', '$scope', '$http', 'toastr',
    function ($rootScope, $scope, $http, toastr) {

        $rootScope.navigation = {selection: 'studentPayments'};

        $scope.departmentList = [];
        $scope.department = {};
        $scope.maxSize = 10;
        $scope.itemsPerPage = 0;
        $scope.totalItems = 0;
        $scope.currentPage = 1;

        $scope.editMode = false;

        $http.get('app/api/v1/student/allStudent').success(function (rs) {
            $scope.studentList = rs;
        }).error(function (e) {
            $scope.studentList = [];
        });

        $scope.pageChanged = function () {
            if (!$scope.departmentName) {
                name = '*';
            } else {
                name = $scope.departmentName;
            }
            $http.get('app/api/v1/department/all', {
                params: {
                    page: $scope.currentPage,
                    name: name
                }
            }).success(function (rs) {
                $scope.departmentList = rs.content;
                $scope.totalItems = rs.totalElements;
                $scope.itemsPerPage = rs.size;
            }).error(function (e) {
                $scope.departmentList = [];
                console.log(e);
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

        $scope.pageChanged();
    }]);