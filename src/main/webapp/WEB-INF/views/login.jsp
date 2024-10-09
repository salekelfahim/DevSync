<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
</head>
<body class="bg-gray-50 dark:bg-gray-900">
<section class="flex justify-center items-center min-h-screen px-4">
    <div class="w-full max-w-3xl p-8 bg-white rounded-lg shadow dark:bg-gray-800 lg:px-12">
        <h1 class="text-3xl font-semibold text-gray-800 capitalize dark:text-white mb-6">Login</h1>

        <form class="grid grid-cols-1 gap-6" action="login" method="post">
            <div>
                <label class="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200">Email</label>
                <input type="email" name="email" class="block w-full px-4 py-2 mt-2 text-gray-800 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 dark:placeholder-gray-500 focus:border-blue-500 focus:ring-blue-500 focus:outline-none focus:ring focus:ring-opacity-50" required>
            </div>

            <div>
                <label class="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200">Password</label>
                <input type="password" name="password" class="block w-full px-4 py-2 mt-2 text-gray-800 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 dark:placeholder-gray-500 focus:border-blue-500 focus:ring-blue-500 focus:outline-none focus:ring focus:ring-opacity-50" required>
            </div>

            <button type="submit" class="flex items-center justify-center w-full px-6 py-3 mt-4 text-white bg-blue-600 rounded-lg hover:bg-blue-500 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-50">
                <span>Login</span>
            </button>
        </form>
        <a href="users?action=create" class="mt-4 block text-center text-blue-600 hover:underline">Create Account</a>
    </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>