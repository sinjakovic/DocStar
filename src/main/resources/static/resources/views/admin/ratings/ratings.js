/**
 * Created by Brandon on 4/21/2017.
 */
ratings = angular.module('Docstar.Admin.Ratings', ['ngRoute', 'Docstar.Services']);

ratings.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/admin/ratings', {
        templateUrl: 'resources/views/shared/ratings/ratings.html',
        controller: 'Docstar.Admin.Ratings.Controller'
    });
}]);

ratings.controller('Docstar.Admin.Ratings.Controller', ['$scope', '$resource', function ($scope, $resource) {
    var Ratings = $resource('/api/v1/admin/ratings/');
    $scope.ratings = Ratings.query();
    $scope.role = 'admin';
}]);