/**
 * Created by Manoj Janaka on 14-11-2016.
 */
activitiAdminApp.controller('StudentController', ['$rootScope', '$scope', '$http', 'toastr',
    function ($rootScope, $scope, $http, toastr) {

        $rootScope.navigation = {selection: 'student'};

        $scope.studentList = [];
        $scope.courseList = [];
        $scope.student = {};
        $scope.maxSize = 10;
        $scope.itemsPerPage = 0;
        $scope.totalItems = 0;
        $scope.currentPage = 1;

        $scope.editMode = false;

        $scope.pageChanged = function () {
            if (!$scope.studentName) {
                name = '*';
            } else {
                name = $scope.studentName;
            }
            $http.get('app/api/v1/student/all', {
                params: {
                    page: $scope.currentPage,
                    name: name
                }
            }).success(function (rs) {
                $scope.studentList = rs.content;
                $scope.totalItems = rs.totalElements;
                $scope.itemsPerPage = rs.size;
            }).error(function (e) {
                $scope.studentList = [];
                console.log(e);
            });
        };

        $scope.add = function () {
            $http.post('app/api/v1/student/save', $scope.student).success(function (data) {
                toastr.success('Successfully Saved !!');
                $scope.pageChanged();
                $scope.reset();
            }).error(function (data) {
                toastr.error('Failed to Save !!');
            });
        };

        $scope.update = function () {
            $http.post('app/api/v1/student/update', $scope.student).success(function (data) {
                toastr.success('Successfully Updated !!');
                $scope.pageChanged();
                $scope.reset();
            }).error(function (data) {
                toastr.error(data.message);
            });
        };

        $scope.edit = function (student) {
            $scope.student = student;
            $scope.student.dateOfBirth=new Date($scope.student.dateOfBirth);
            $scope.editMode = true;
        };

        $scope.delete = function (id) {
            $http.delete('app/api/v1/student/delete/' + id).success(function (data) {
                toastr.success('Successfully Deleted !!');
                $scope.pageChanged();
            }).error(function (data) {
                toastr.error(data.message);
            });
        };

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

        $scope.reset = function () {
            $scope.student = {};
            $scope.editMode = false;
        };

        $scope.search = function () {
            $scope.pageChanged();
        };

        $scope.pageChanged();
    }]);