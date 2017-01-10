/**
 * Created by Manoj Janaka on 14-11-2016.
 */
activitiAdminApp.controller('CourseWisePaymentController', ['$rootScope', '$scope', '$http', 'toastr',
    function ($rootScope, $scope, $http, toastr) {

        $rootScope.navigation = {selection: 'coursePayment'};

        $scope.coursePaymentList = [];
        $scope.courseList = [];
        $scope.coursePayment = {};
        $scope.maxSize = 10;
        $scope.itemsPerPage = 0;
        $scope.totalItems = 0;
        $scope.currentPage = 1;

        $scope.editMode = false;

        $http.get('app/api/v1/department/allActiveDepartments').success(function (rs) {
            $scope.departmentList = rs;
        }).error(function (e) {
            $scope.departmentList = [];
        });

        $http.get('app/api/v1/course/allCourse').success(function (rs) {
            $scope.courseList = rs;
        }).error(function (e) {
            $scope.courseList = [];
        });


        $http.get('app/api/v1/payment_type/allActivePaymentTypes').success(function (rs) {
            $scope.paymentTypeList = rs;
        }).error(function (e) {
            $scope.paymentTypeList = [];
        });

        $scope.pageChanged = function () {
            if (!$scope.coursePaymentName) {
                name = '*';
            } else {
                name = $scope.coursePaymentName;
            }
            $http.get('app/api/v1/coursePayment/all', {
                params: {
                    page: $scope.currentPage,
                    name: name
                }
            }).success(function (rs) {
                $scope.coursePaymentList = rs.content;
                $scope.totalItems = rs.totalElements;
                $scope.itemsPerPage = rs.size;
            }).error(function (e) {
                $scope.coursePaymentList = [];
                console.log(e);
            });
        };

        $scope.add = function () {
            $http.post('app/api/v1/coursePayment/save', $scope.coursePayment).success(function (data) {
                toastr.success('Successfully Saved !!');
                $scope.pageChanged();
                $scope.reset();
            }).error(function (data) {
                toastr.error('Failed to Save !!');
            });
        };

        $scope.update = function () {
            $http.post('app/api/v1/coursePayment/update', $scope.coursePayment).success(function (data) {
                toastr.success('Successfully Updated !!');
                $scope.pageChanged();
                $scope.reset();
            }).error(function (data) {
                toastr.error(data.message);
            });
        };

        $scope.edit = function (coursePayment) {
            $scope.coursePayment = coursePayment;
            $scope.editMode = true;
        };

        $scope.delete = function (id) {
            $http.delete('app/api/v1/coursePayment/delete/' + id).success(function (data) {
                toastr.success('Successfully Deleted !!');
                $scope.pageChanged();
            }).error(function (data) {
                toastr.error(data.message);
            });
        };

        $scope.reset = function () {
            $scope.coursePayment = {};
            $scope.editMode = false;
        };

        $scope.search = function () {
            $scope.pageChanged();
        };

        $scope.pageChanged();
    }]);