/**
 * Created by Manoj Janaka on 14-11-2016.
 */
activitiAdminApp.controller('ExamController', ['$rootScope', '$scope', '$http', 'toastr',
    function ($rootScope, $scope, $http, toastr) {

        $rootScope.navigation = {selection: 'exam'};

        $scope.examList = [];
        $scope.courseList = [];
        $scope.exam = {};
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

        $scope.pageChanged = function () {
            if (!$scope.examName) {
                name = '*';
            } else {
                name = $scope.examName;
            }
            $http.get('app/api/v1/exam/all', {
                params: {
                    page: $scope.currentPage,
                    name: name
                }
            }).success(function (rs) {
                $scope.examList = rs.content;
                $scope.totalItems = rs.totalElements;
                $scope.itemsPerPage = rs.size;
            }).error(function (e) {
                $scope.examList = [];
                console.log(e);
            });
        };

        $scope.add = function () {
            $http.post('app/api/v1/exam/save', $scope.exam).success(function (data) {
                toastr.success('Successfully Saved !!');
                $scope.pageChanged();
                $scope.reset();
            }).error(function (data) {
                toastr.error('Failed to Save !!');
            });
        };

        $scope.update = function () {
            $http.post('app/api/v1/exam/update', $scope.exam).success(function (data) {
                toastr.success('Successfully Updated !!');
                $scope.pageChanged();
                $scope.reset();
            }).error(function (data) {
                toastr.error(data.message);
            });
        };

        $scope.edit = function (exam) {
            $scope.exam = exam;
            $scope.editMode = true;
        };

        $scope.delete = function (id) {
            $http.delete('app/api/v1/exam/delete/' + id).success(function (data) {
                toastr.success('Successfully Deleted !!');
                $scope.pageChanged();
            }).error(function (data) {
                toastr.error(data.message);
            });
        };

        $scope.reset = function () {
            $scope.exam = {};
            $scope.editMode = false;
        };

        $scope.search = function () {
            $scope.pageChanged();
        };

        $scope.pageChanged();
    }]);