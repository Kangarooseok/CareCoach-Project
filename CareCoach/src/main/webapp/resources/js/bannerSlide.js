/**
 * 
 */

document.addEventListener("DOMContentLoaded", () => {
        const slider = document.querySelector(".slider");
        const slides = document.querySelectorAll(".slide");
        let currentIndex = 0;

        function showSlide(index) {
          const totalSlides = slides.length;
          if (index >= totalSlides) {
            currentIndex = 0; // Loop back to the first slide
          } else if (index < 0) {
            currentIndex = totalSlides - 1; // Loop to the last slide
          } else {
            currentIndex = index;
          }
          const offset = -currentIndex * 100; // Calculate the offset
          slider.style.transform = `translateX(${offset}%)`; // Apply transformation
        }

        // Auto-slide functionality
        setInterval(() => {
          showSlide(currentIndex + 1);
        }, 3000); // Change every 3 seconds

        // Manual navigation (arrows)
        const leftArrow = document.querySelector(".arrow.left");
        const rightArrow = document.querySelector(".arrow.right");

        leftArrow.addEventListener("click", () => {
          showSlide(currentIndex - 1);
        });

        rightArrow.addEventListener("click", () => {
          showSlide(currentIndex + 1);
        });
      });