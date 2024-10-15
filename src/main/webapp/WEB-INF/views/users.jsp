<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
    <title>DevSync - Users</title>
</head>
<body class="bg-gray-900 text-white">
<section class="container px-6 mx-auto py-10">
    <div class="sm:flex sm:items-center sm:justify-between">
        <h2 class="text-lg font-medium text-gray-200 dark:text-white">List of Users</h2>
        <div class="flex items-center mt-4 gap-x-3">
            <a href="users?action=create" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">Add User</a>
            <a href="tags?action=list" class="text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-green-600 dark:hover:bg-green-700 focus:outline-none dark:focus:ring-green-800">Tags</a>
            <a href="tasks?action=list" class="text-white bg-yellow-600 hover:bg-yellow-700 focus:ring-4 focus:ring-yellow-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-yellow-500 dark:hover:bg-yellow-600 focus:outline-none dark:focus:ring-yellow-400">Tasks</a>
            <a href="requests?action=list" class="text-white bg-purple-600 hover:bg-purple-700 focus:ring-4 focus:ring-purple-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-purple-500 dark:hover:bg-purple-600 focus:outline-none dark:focus:ring-purple-400">Requests</a>
            <form action="${pageContext.request.contextPath}/users?action=logout" method="get">
                <button type="submit" class="px-4 py-2 text-white bg-red-600 rounded hover:bg-red-700">
                    Logout
                </button>
            </form>
        </div>
    </div>

    <div class="flex flex-col mt-6">
        <div class="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
            <div class="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
                <div class="overflow-hidden border border-gray-200 dark:border-gray-700 md:rounded-lg">
                    <table class="min-w-full divide-y divide-gray-200 dark:divide-gray-700">
                        <thead class="bg-gray-800">
                        <tr>
                            <th scope="col" class="py-3.5 px-4 text-sm font-normal text-left text-gray-400 dark:text-gray-400">
                                <div class="flex items-center gap-x-3">
                                    <input type="checkbox" class="text-blue-500 border-gray-300 rounded dark:bg-gray-900 dark:ring-offset-gray-900 dark:border-gray-700">
                                    <span>Name</span>
                                </div>
                            </th>
                            <th scope="col" class="px-12 py-3.5 text-sm font-normal text-left text-gray-400 dark:text-gray-400">Email</th>
                            <th scope="col" class="px-4 py-3.5 text-sm font-normal text-left text-gray-400 dark:text-gray-400">Password (Hidden)</th>
                            <th scope="col" class="px-4 py-3.5 text-sm font-normal text-left text-gray-400 dark:text-gray-400">Role</th>
                            <th scope="col" class="relative py-3.5 px-4">Actions</th>
                        </tr>
                        </thead>
                        <tbody class="bg-gray-900 divide-y divide-gray-700">
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td class="px-4 py-4 text-sm font-medium text-gray-200 whitespace-nowrap">
                                    <div class="flex items-center gap-x-3">
                                        <div class="flex items-center gap-x-2">
                                            <div class="flex items-center justify-center w-8 h-8 text-blue-500 bg-blue-100 rounded-full dark:bg-gray-800">
                                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-5 h-5">
                                                    <path stroke-linecap="round" stroke-linejoin="round" d="M2.25 15.75l5.159-5.159a2.25 2.25 0 013.182 0l5.159 5.159m-1.5-1.5l1.409-1.409a2.25 2.25 0 013.182 0l2.909 2.909m-18 3.75h16.5a1.5 1.5 0 001.5-1.5V6a1.5 1.5 0 00-1.5-1.5H3.75A1.5 1.5 0 002.25 6v12a1.5 1.5 0 001.5 1.5zm10.5-11.25h.008v.008h-.008V8.25zm.375 0a.375.375 0 11-.75 0 .375.375 0 01.75 0z" />
                                                </svg>
                                            </div>
                                            <div>
                                                <h2 class="font-normal text-gray-200 dark:text-white">${user.firstName} ${user.lastName}</h2>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td class="px-12 py-4 text-sm font-normal text-gray-200 whitespace-nowrap">${user.email}</td>
                                <td class="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">********</td>
                                <td class="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">${user.role}</td>
                                <td class="px-4 py-4 text-sm whitespace-nowrap">
                                    <div class="flex gap-2">
                                        <a href="users?action=edit&id=${user.id}" class="px-4 py-2 bg-green-500 border rounded-lg text-sm font-medium text-gray-200 transition-colors duration-200 hover:bg-green-600 dark:hover:bg-gray-800">
                                            Edit
                                        </a>
                                        <a href="users?action=delete&id=${user.id}" class="px-4 py-2 bg-red-500 border rounded-lg text-sm font-medium text-gray-200 transition-colors duration-200 hover:bg-red-600 dark:hover:bg-gray-800">
                                            Delete
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>