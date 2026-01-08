import hljs from 'highlight.js';
import 'highlight.js/scss/default.scss';
import './quiz.css';
import './dialog';
import './final-score';
import './leaderboard';
import './module';
import './start-quiz';

hljs.highlightAll();


function redirectToNextModuleIfQuizInProgress() {
    // Only perform redirection logic on the homepage.
    if (!window.location.pathname.includes('leaderboard')) {
        const currentQuizJSON = localStorage.getItem('currentQuiz');
        if (currentQuizJSON) {
            const currentQuiz = JSON.parse(currentQuizJSON);
            if (currentQuiz.nextModuleUrl && currentQuiz.nextModuleUrl !== window.location.pathname) {
                //window.location.href = currentQuiz.nextModuleUrl;
            }
        }
    }
}

// ============================================
// Theme Toggle Functionality
// ============================================
(function() {
    'use strict';
    function updateThemeIcons(theme) {
        const sunIcons = document.querySelectorAll('.toggle-light');
        const moonIcons = document.querySelectorAll('.toggle-dark');
        const themeTexts = document.querySelectorAll('.theme-text');

        const isDark = theme === 'dark';

        sunIcons.forEach(icon => icon.classList.toggle('hidden', !isDark));
        moonIcons.forEach(icon => icon.classList.toggle('hidden', isDark));
        themeTexts.forEach(text => text.textContent = isDark ? 'LIGHT MODE' : 'DARK MODE');
    }

    function setTheme(theme){
        document.documentElement.removeAttribute('data-theme');
        if (theme === 'dark') {
            document.documentElement.setAttribute('data-theme','dark');
            localStorage.setItem('theme', 'dark');
        } else {
            document.documentElement.setAttribute('data-theme','light');
            localStorage.setItem('theme', 'light');
        }
        updateThemeIcons(theme);
    }

    function toggleTheme() {
        const currentTheme = document.documentElement.getAttribute('data-theme') === 'dark'
            ? 'dark'
            : 'light';
        setTheme(currentTheme === 'dark' ? 'light' : 'dark');
    }

    document.addEventListener('DOMContentLoaded', () => {
        const savedTheme = localStorage.getItem('theme') || 'light';
        setTheme(savedTheme);

        document.querySelectorAll('.theme-toggle').forEach(btn => {
            btn.addEventListener('click', toggleTheme);
        });
    });
})();


window.common = {
    redirectToNextModuleIfQuizInProgress: redirectToNextModuleIfQuizInProgress,
}