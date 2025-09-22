<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<footer id="footer" class="footer">
    <!--
    <div class="footer-newsletter">
      <div class="container">
        <div class="row justify-content-center text-center">
          <div class="col-lg-6">
            <h4>Join Our Newsletter</h4>
            <p>Subscribe to our newsletter and receive the latest news about our products and services!</p>
            <form action="forms/newsletter.php" method="post" class="php-email-form">
              <div class="newsletter-form"><input type="email" name="email"><input type="submit" value="Subscribe"></div>
              <div class="loading">Loading</div>
              <div class="error-message"></div>
              <div class="sent-message">Your subscription request has been sent. Thank you!</div>
            </form>
          </div>
        </div>
      </div>
    </div>-->

    <div class="container footer-top">
      <div class="row gy-4">
        <div class="col-lg-4 col-md-6 footer-about">
          <a href="" class="d-flex align-items-center">
            <span class="sitename">&lt;BR&gt;</span>
          </a>
          <div class="footer-contact pt-3">
            <p>서울특별시 종로구 창경궁로 254 동원빌딩 7층</p>
            <p class="mt-3"><strong>대표번호:</strong> <span>02-2188-6900</span></p>
           
          </div>

          <p class="mt-2">
            <!-- footer ìª½ -->
            <span onclick="toggleFooterMap()" style="cursor: pointer; color: #20c997; text-decoration: underline;">위치</span>

          </p>
          
          <div id="map" style="width: 100%; height: 400px; display: none; margin-top: 20px;"></div>
        </div>

        <div class="col-lg-3 offset-lg-1 col-md-3 footer-links">
          <h4>Useful Links</h4>
          <ul>
            <li><i class="bi bi-chevron-right"></i> <a href="#hero">Home</a></li>
            <li><i class="bi bi-chevron-right"></i> <a href="#about">About</a></li>
            <li><i class="bi bi-chevron-right"></i> <a href="#services">Services</a></li>
            <!--<li><i class="bi bi-chevron-right"></i> <a href="#team">Team</a></li>-->
          </ul>
        </div>
        <!--
        <div class="col-lg-2 col-md-3 footer-links">
          <h4>Our Services</h4>
          <ul>
            <li><i class="bi bi-chevron-right"></i> <a href="#">Web Design</a></li>
            <li><i class="bi bi-chevron-right"></i> <a href="#">Web Development</a></li>
            <li><i class="bi bi-chevron-right"></i> <a href="#">Product Management</a></li>
            <li><i class="bi bi-chevron-right"></i> <a href="#">Marketing</a></li>
          </ul>
        </div>-->

        <div class="col-lg-4 col-md-12 text-center">
          <h4>Follow Us</h4>
          <div class="social-links d-flex justify-content-center" style="gap: 15px;">
            <!-- ë¤ì´ë² ë¸ë¡ê·¸ -->
            <a href="https://blog.naver.com/kosapr" target="_blank"
               style="width: 48px; height: 48px; border: 1px solid #ccc; border-radius: 50%; display: flex; align-items: center; justify-content: center;">
              <img src="assets/img/naverblog.png" alt="ë¤ì´ë²ë¸ë¡ê·¸" style="width: 28px; height: 28px;">
            </a>
          
            <!-- íì´ì¤ë¶ -->
            <a href="https://www.facebook.com/people/íêµ­ìíí¸ì¨ì´ì°ìíí/61550673369738/" target="_blank"
               style="width: 48px; height: 48px; border: 1px solid #ccc; border-radius: 50%; display: flex; align-items: center; justify-content: center;">
              <i class="bi bi-facebook" style="font-size: 24px;"></i>
            </a>
          
            <!-- ì¹´ì¹´ì¤í¡ -->
            <a href="https://pf.kakao.com/_Zxjfsb/friend" target="_blank"
               style="width: 48px; height: 48px; border: 1px solid #ccc; border-radius: 50%; display: flex; align-items: center; justify-content: center;">
              <img src="assets/img/kakaotalk.png" alt="ì¹´ì¹´ì¤í¡" style="width: 28px; height: 28px;">
            </a>
          
            <!-- ì íë¸ -->
            <a href="https://www.youtube.com/@kosa3223" target="_blank"
               style="width: 48px; height: 48px; border: 1px solid #ccc; border-radius: 50%; display: flex; align-items: center; justify-content: center;">
              <i class="bi bi-youtube" style="font-size: 24px;"></i>
            </a>
          </div>
          
          
        </div>
        

      </div>
    </div>

    <div class="container copyright text-center mt-4">
      <p>Â© <span>Copyright</span> <strong class="px-1 sitename">eNno</strong> <span>All Rights Reserved</span></p>
      <div class="credits">
        <!-- All the links in the footer should remain intact. -->
        <!-- You can delete the links only if you've purchased the pro version. -->
        <!-- Licensing information: https://bootstrapmade.com/license/ -->
        <!-- Purchase the pro version with working PHP/AJAX contact form: [buy-url] -->
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a> Distributed by <a href="https://themewagon.com">ThemeWagon
      </a></div><a href="https://themewagon.com">
    </a></div><a href="https://themewagon.com">

  </a>

  </footer>