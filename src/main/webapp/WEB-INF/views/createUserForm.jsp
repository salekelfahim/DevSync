<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New User</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
</head>

<body class="bg-gray-50 dark:bg-gray-900">
<section class="flex justify-center items-center min-h-screen px-4">
    <div class="w-full max-w-3xl p-8 bg-white rounded-lg shadow dark:bg-gray-800 lg:px-12">
        <h1 class="text-3xl font-semibold text-gray-800 capitalize dark:text-white mb-6">Add New User</h1>

        <form class="grid grid-cols-1 gap-6 md:grid-cols-2" action="users?action=create" method="post">
            <div>
                <label class="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200">First Name</label>
                <input type="text" name="firstName" placeholder="John" class="block w-full px-4 py-2 mt-2 text-gray-800 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 dark:placeholder-gray-500 focus:border-blue-500 focus:ring-blue-500 focus:outline-none focus:ring focus:ring-opacity-50" />
            </div>

            <div>
                <label class="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200">Last Name</label>
                <input type="text" name="lastName" placeholder="Snow" class="block w-full px-4 py-2 mt-2 text-gray-800 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 dark:placeholder-gray-500 focus:border-blue-500 focus:ring-blue-500 focus:outline-none focus:ring focus:ring-opacity-50" />
            </div>

            <div>
                <label class="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200">Email Address</label>
                <input type="email" name="email" placeholder="johnsnow@example.com" class="block w-full px-4 py-2 mt-2 text-gray-800 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 dark:placeholder-gray-500 focus:border-blue-500 focus:ring-blue-500 focus:outline-none focus:ring focus:ring-opacity-50" />
            </div>

            <div>
                <label class="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200">Password</label>
                <input type="password" name="password" placeholder="Enter your password" class="block w-full px-4 py-2 mt-2 text-gray-800 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 dark:placeholder-gray-500 focus:border-blue-500 focus:ring-blue-500 focus:outline-none focus:ring focus:ring-opacity-50" />
            </div>

            <div>
                <label class="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200">Role</label>
                <select name="role" class="block w-full px-4 py-2 mt-2 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 dark:placeholder-gray-500 focus:border-blue-500 focus:ring-blue-500 focus:outline-none focus:ring focus:ring-opacity-50">
                    <option selected disabled>Choose a role</option>
                    <option value="MANAGER">Manager</option>
                    <option value="USER">User</option>
                </select>
            </div>

            <div></div>

            <button type="submit" class="col-span-2 flex items-center justify-center w-full px-6 py-3 mt-4 text-white bg-blue-600 rounded-lg hover:bg-blue-500 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-50">
                <span>Create User</span>
            </button>
        </form>
    </div>
</section>

<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>
