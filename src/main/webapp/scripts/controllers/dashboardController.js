/**
 * Created by Amila-Kumara on 3/19/2016.
 */
activitiAdminApp.controller('DashboardController', ['$rootScope', '$scope', '$http', 'toastr',
    function ($rootScope, $scope, $http, toastr) {
        $rootScope.navigation = {selection: 'dashboard'};

        $scope.loadAllDepartments = function () {
            $http.get('app/api/v1/department/allActiveDepartments').success(function (rs) {
                $scope.departmentList = rs;
            }).error(function (e) {
                $scope.departmentList = [];
                console.log(e);
            });
        };

        $scope.loadAllDepartments();

    }]);