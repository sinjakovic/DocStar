tmod = angular.module('Docstar.Login', ['ngRoute']);

tmod.config(['$routeProvider', function ($routeProvider) {
    var config = {
        templateUrl: '/resources/views/shared/login/login.html',
        controller: 'Docstar.Login.Controller'
    };

    $routeProvider.when('/', config).when('/login', config);
}]);

tmod.controller('Docstar.Login.Controller', ['$scope', '$http', '$location', 'Session.Service', function ($scope, $http, $location, Session) {
    $scope.username = '';
    $scope.password = '';
    $scope.hasError = false;

    $scope.isLoggedIn = Session.isAuthenticated;

    if (Session.isAuthenticated()) {
        $location.path('/user/documents');
    }

    $scope.login = function () {
        var req = {
            method: 'POST',
            url: '/login',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: 'username=' + $scope.username + '&password=' + $scope.password
        };

        $http(req).then(
            function (response) { // handles all status codes between 200-299.
                $scope.hasError = false;
                delete response.data.password;
                Session.create(response.data);
                $location.url($location.path());
                $location.url('/user/documents');
            },

            function (error) {
                $scope.hasError = true;
            }
        );
    }
}]);