/**
 * Created by Brandon on 4/24/2017.
 */
var adminLists = angular.module('Docstar.Admin.Lists', ['ngRoute', 'ngResource', 'ui.bootstrap', 'Session']);

adminLists.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/admin/lists', {
        templateUrl: 'resources/views/admin/lists/lists.html',
        controller: 'Docstar.Admin.Lists.Controller'
    })
        .when('/admin/lists/:lid', {
            templateUrl : 'resources/views/admin/lists/details.html',
            controller : 'Docstar.Admin.Lists.Details.Controller'
        });
}]);

adminLists.controller('Docstar.Admin.Lists.Controller', ['$scope', '$resource', '$uibModal', function( $scope, $resource, $modal ) {

    var Lists = $resource('/api/v1/admin/lists/', {});
    $scope.lists = Lists.query();
    $scope.createList = function(){
        var modal = $modal.open( {
                templateUrl : 'resources/views/admin/lists/newList.html',
                controller : 'Docstar.Lists.NewListController',

            }
        );

        modal.result.then( function( newList ) {
            Lists.save( newList,
                function( savedList ) {
                    $scope.lists.push(savedList);
                } );
        } );
    }

    $scope.role = 'admin';
}]);