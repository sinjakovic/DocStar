/**
 * Created by Brandon on 4/21/2017.
 */
var docs = angular.module('Docstar.Users.Documents', ['ngRoute', 'ngResource', 'Docstar.Services']);

docs.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/user/documents', {
        templateUrl: 'resources/views/users/documents/documents.html',
        controller: 'Docstar.Users.Documents.Controller'
    })
        .when('/user/documents/:dID', {
            templateUrl : 'resources/views/users/documents/details.html',
            controller : 'Docstar.Users.Documents.Details.Controller'
        });
}]);

docs.controller('Docstar.Users.Documents.Controller', ['$scope', '$resource', function( $scope, $resource ) {
    var Docs = $resource('api/v1/user/documents', {});

    $scope.docs = Docs.get({});
}]);