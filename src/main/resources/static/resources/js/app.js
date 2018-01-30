/**
 * Created by Brandon on 4/20/2017.
 */
var app = angular.module('Docstar.App', ['ngRoute',
    'spring-security-csrf-token-interceptor',
    'Session',
    'Docstar.Login',
    'Docstar.Users.Documents',
    'Docstar.Users',
    'Docstar.Profile',
    'Docstar.Admin.Ratings',
    'Docstar.User.Ratings',
    'Docstar.Admin.Documents',
    'Docstar.Admin.Lists',
    'checklist-model']);

app.controller('Docstar.Navigation.Controller', ['$scope', '$http', '$location', 'Session.Service', function ($scope, $http, $location, Session) {
    $scope.isAuthenticated = Session.isAuthenticated;

    $scope.username = function () {
        var user = Session.user();
        return user && user.username;
    };

    $scope.hasRole = Session.hasRole;

    $scope.logout = function () {
        var req = {
            method: 'POST',
            url: '/logout'
        };

        $http(req).then(function () {
            Session.destroy();
            $location.path('/login');
        });
    }
}]);

app.factory('AuthInterceptor', ['$q', '$location', 'Session.Service', function ($q, $location, Session) {
    return {
        'responseError': function (rejection) {
            Session.destroy();
            $location.path('/login');
            return $q.reject(rejection);
        }
    }
}]);


app.config(['$routeProvider', '$httpProvider',
    function ($routeProvider, $httpProvider) {
        $routeProvider.otherwise({
            redirectTo: '/login'
        });
    }]);