<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
    <title>DevSync - Tags</title>
</head>
<body class="bg-gray-900 text-white flex">
<section class="bg-gray-800 dark:bg-gray-900 flex-grow">
    <div class="container px-6 py-10 mx-auto">
        <h1 class="text-3xl font-extrabold text-center text-white capitalize lg:text-4xl">Tasks's <span class="text-blue-500">Tags</span></h1>
        <a href="users?action=list" class="text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-green-600 dark:hover:bg-green-700 focus:outline-none dark:focus:ring-green-800">Home</a>

        <div class="grid grid-cols-1 gap-4 mt-8 xl:mt-12 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
            <c:forEach var="tag" items="${tags}">
                <div class="flex flex-col items-center p-6 space-y-3 text-center bg-gray-700 rounded-xl shadow-lg">
                <span class="inline-block p-3 text-blue-500 bg-blue-100 rounded-full dark:text-white dark:bg-blue-500">
                    <svg xmlns="http://www.w3.org/2000/svg" class="w-6 h-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z" />
                    </svg>
                </span>

                    <h1 class="text-xl font-semibold text-gray-200 capitalize dark:text-white">${tag.name}</h1>

                    <a href="tags?action=delete&id=${tag.id}" class="flex items-center text-sm text-blue-500 capitalize transition-colors duration-300 transform hover:underline hover:text-blue-600 dark:text-blue-400 dark:hover:text-blue-500">
                        <span>Delete</span>
                    </a>
                </div>
            </c:forEach>

            <!-- Modal toggle -->
            <button data-modal-target="add-tag-modal" data-modal-toggle="add-tag-modal" class="flex flex-col items-center p-6 space-y-3 text-center bg-gray-700 rounded-xl shadow-lg" type="button">
                <span class="inline-block p-3 text-blue-500 bg-blue-100 rounded-full dark:text-white dark:bg-blue-500">
                    <svg xmlns="http://www.w3.org/2000/svg" class="w-6 h-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4" />
                    </svg>
                </span>
                <h1 class="text-xl font-semibold text-gray-200 capitalize dark:text-white">Add Tag</h1>
            </button>

            <!-- Modal -->
            <div id="add-tag-modal" tabindex="-1" aria-hidden="true" class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
                <div class="relative p-4 w-full max-w-md max-h-full">
                    <div class="relative bg-gray-800 rounded-lg shadow dark:bg-gray-700">
                        <div class="flex items-center justify-between p-4 border-b rounded-t dark:border-gray-600">
                            <h3 class="text-xl font-semibold text-gray-200 dark:text-white">Create New Tag</h3>
                            <button type="button" class="text-gray-400 bg-transparent hover:bg-gray-700 hover:text-white rounded-lg text-sm w-8 h-8 inline-flex justify-center items-center" data-modal-hide="add-tag-modal">
                                <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
                                </svg>
                                <span class="sr-only">Close modal</span>
                            </button>
                        </div>
                        <div class="p-4">
                            <form action="tags?action=create" method="post" class="space-y-4">
                                <div>
                                    <label for="name" class="block mb-2 text-sm font-medium text-gray-200 dark:text-white">Tag Name</label>
                                    <input type="text" name="name" id="name" class="bg-gray-700 border border-gray-600 text-gray-200 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400" required />
                                </div>

                                <button type="submit" class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Add</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>