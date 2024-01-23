<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.apache.catalina.tribes.ChannelSender"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Home</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="DevFolio/assets/img/favicon.png" rel="icon">
<link href="DevFolio/assets/img/apple-touch-icon.png"
	rel="apple-touch-icon">

<!-- Vendor CSS Files -->
<link href="DevFolio/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="DevFolio/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="DevFolio/assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link href="DevFolio/assets/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
<link href="DevFolio/assets/css/style.css" rel="stylesheet">

<!-- =======================================================
  * Template Name: DevFolio
  * Updated: Jan 09 2024 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/devfolio-bootstrap-portfolio-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>
<body>
	<% 
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (session.getAttribute("username") == null) {
            response.sendRedirect("Login.html");
        } else {
            // Nếu đã đăng nhập, tiếp tục hiển thị trang
    %>
	<!-- ======= Header ======= -->
	<header id="header" class="fixed-top">
		<div
			class="container d-flex align-items-center justify-content-between">

			<h1 class="logo">
				<a href="Index.html">DevFolio</a>
			</h1>
			<!-- Uncomment below if you prefer to use an image logo -->
			<!-- <a href="index.html" class="logo"><img src="DevFolio/assets/img/logo.png" alt="" class="img-fluid"></a>-->

			<nav id="navbar" class="navbar">
				<ul>
					<li><a class="nav-link scrollto active" href="#hero">Home</a></li>
					<li><a class="nav-link" href="./UserProfile.html">User Profile</a></li>
					<li><a class="nav-link" href="./Admin.html">Admin Page</a></li>
				</ul>
				<i class="bi bi-list mobile-nav-toggle"></i>
			</nav>
			<!-- .navbar -->

		</div>
	</header>
	<!-- End Header -->

	<!-- ======= Hero Section ======= -->
	<div id="hero" class="hero route bg-image"
		style="background-image: url(DevFolio/assets/img/hero-bg.jpg)">
		<div class="overlay-itro"></div>
		<div class="hero-content display-table">
			<div class="table-cell">
				<div class="container">
					<!--<p class="display-6 color-d">Hello, world!</p>-->
					<h1 class="hero-title mb-4">I am Giap Van Tai</h1>
					<p class="hero-subtitle">
						<span class="typed"
							data-typed-items="Designer, Developer, Freelancer, Gamer"></span>
					</p>
					<!-- <p class="pt-3"><a class="btn btn-primary btn js-scroll px-4" href="#about" role="button">Learn More</a></p> -->
				</div>
			</div>
		</div>
	</div>
	<!-- End Hero Section -->

	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<% 
        }
    %>
	<!-- Vendor JS Files -->
	<script src="DevFolio/assets/vendor/purecounter/purecounter_vanilla.js"></script>
	<script
		src="DevFolio/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="DevFolio/assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script src="DevFolio/assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="DevFolio/assets/vendor/typed.js/typed.umd.js"></script>
	<script src="DevFolio/assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="DevFolio/assets/js/main.js"></script>
</body>
</html>
