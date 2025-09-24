<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KOSA 사원관리시스템</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
<style>
    .navbar-brand {
        font-weight: bold;
        font-size: 1.5rem;
    }
    .nav-link {
        font-weight: 500;
        transition: all 0.3s ease;
    }
    .nav-link:hover {
        transform: translateY(-1px);
        color: #007bff !important;
    }
    .navbar {
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="emplist.do">
        <i class="fas fa-users me-2"></i>KOSA 사원관리시스템
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="emplist.do">
              <i class="fas fa-list me-1"></i>사원목록
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="emp.do">
              <i class="fas fa-user-plus me-1"></i>사원등록
          </a>
        </li>
      </ul>
      <div class="d-flex">
        <form class="d-flex" action="empsearch.do" method="get">
          <input class="form-control me-2" type="search" name="searchName" placeholder="사원명 검색" aria-label="Search">
          <button class="btn btn-outline-light" type="submit">
              <i class="fas fa-search"></i>
          </button>
        </form>
      </div>
    </div>
  </div>
</nav>
</body>
</html>