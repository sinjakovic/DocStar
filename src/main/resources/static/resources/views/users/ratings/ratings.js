/**
 * Created by Brandon on 4/21/2017.
 */
ratings = angular.module('Docstar.User.Ratings', ['ngRoute', 'Docstar.Services']);

ratings.config(['$routeProvider', function( $routeProvider ) {
    $routeProvider.when('/user/ratings', {
        templateUrl: 'resources/views/shared/ratings/ratings.html',
        controller: 'Docstar.User.Ratings.Controller'
    });
}]);

ratings.controller('Docstar.User.Ratings.Controller', ['$scope', '$resource', function( $scope, $resource ) {
    var Ratings = $resource('/api/v1/user/ratings');

    $scope.ratings = Ratings.query();

    $scope.role = 'user';
}]);