/**
 * Created by Manoj Janaka on 14-11-2016.
 */
activitiAdminApp.controller('CourseController', ['$rootScope', '$scope', '$http', 'toastr',
    function ($rootScope, $scope, $http, toastr) {

        $rootScope.navigation = {selection: 'course'};

        $scope.courseList = [];
        $scope.course = {};
        $scope.yearList = [];
        $scope.maxSize = 10;
        $scope.itemsPerPage = 0;
        $scope.totalItems = 0;
        $scope.currentPage = 1;

        $scope.editMode = false;

        var date = new Date;
        var year = date.getFullYear() - 2;

        for (var i = year; i < year + 6; i++) {
            $scope.yearList.push(i);
        }

        $scope.open1 = function() {
            $scope.popup1.opened = true;
        };

        $http.get('app/api/v1/department/allActiveDepartments').success(function (rs) {
            $scope.departmentList = rs;
        }).error(function (e) {
            $scope.departmentList = [];
        });

        $http.get('app/rest/users').success(function (rs) {
            $scope.userList = rs;
        }).error(function (e) {
            $scope.userList = [];
        });

        $scope.pageChanged = function () {
            if (!$scope.courseName) {
                name = '*';
            } else {
                name = $scope.courseName;
            }
            $http.get('app/api/v1/course/all', {
                params: {
                    page: $scope.currentPage,
                    name: name
                }
            }).success(function (rs) {
                $scope.courseList = rs.content;
                $scope.totalItems = rs.totalElements;
                $scope.itemsPerPage = rs.size;
            }).error(function (e) {
                $scope.courseList = [];
                console.log(e);
            });
        };

        $scope.add = function () {
            $http.post('app/api/v1/course/save', $scope.course).success(function (data) {
                toastr.success('Successfully Saved !!');
                $scope.pageChanged();
                $scope.reset();
            }).error(function (data) {
                toastr.error('Failed to Save !!');
            });
        };

        $scope.update = function () {
            $http.post('app/api/v1/course/update', $scope.course).success(function (data) {
                toastr.success('Successfully Updated !!');
                $scope.pageChanged();
                $scope.reset();
            }).error(function (data) {
                toastr.error(data.message);
            });
        };

        $scope.edit = function (course) {
            $scope.course = course;
            $scope.editMode = true;
        };

        $scope.delete = function (id) {
            $http.delete('app/api/v1/course/delete/' + id).success(function (data) {
                toastr.success('Successfully Deleted !!');
                $scope.pageChanged();
            }).error(function (data) {
                toastr.error(data.message);
            });
        };

        $scope.reset = function () {
            $scope.course = {};
            $scope.editMode = false;
        };

        $scope.search = function () {
            $scope.pageChanged();
        };

        $scope.pageChanged();
    }]);