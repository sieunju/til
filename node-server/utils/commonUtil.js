const express = require('express')

var randomImageArr = [
    'https://cdn.qtzz.synology.me/resource/img/20210921/1632238064795dwalkkz7dea.png',
    'https://cdn.qtzz.synology.me/resource/img/20210922/1632318929985pivput2yrnh.jpg',
    'https://cdn.qtzz.synology.me/resource/img/20211122/1637586222323fzcfhwey4km.png',
    'https://cdn.qtzz.synology.me/resource/img/20220111/1641903610369o5hb9obe2n.JPG',
    'https://cdn.qtzz.synology.me/resource/img/20230227/1677459493890tm5o2bizusc.jpg',
    'https://cdn.qtzz.synology.me/resource/img/20230227/16774626399811aaisjyf63t.jpg',
    'https://cdn.qtzz.synology.me/resource/img/20230227/1677463363512ckpf7o2nmgc.jpg',
    'https://cdn.qtzz.synology.me/resource/img/20230227/1677463363525o8e0voblm9g.jpg',
]

var randomTitleArr = [
    '안녕하세요',
    '무한한 창의력 발휘',
    '창의적인 아이디어 실현',
    '블랙몬스터',
    '홈브루~',
    '📕 Today, I learned Something',
    ':man-bowing: :man-bowing::man-bowing::man-bowing:',
    '나는야 날으는 돼지'
]

var randomMessageArr = [
    '그대와 처음 만난 이곳 모든날 모든 순간 좋았다.',
    '말했잖아 언젠가 이런 날이 온다면 널 혼자 내버려두지 않을 거라고 죄다 낭떠러지야, 봐 예상했던 것보다 더 아플지도모르지만',
    '스토어. 좋아하는 Apple 제품을 구입하는 가장 좋은 방법',
    '지는 별빛 바라볼때 눈에 흘러 내리는 못다한 말들 그 아픈 사랑',
    '나는야 날으는 도야지! 늦가가가 최신 제품. 따끈따근 신제품 이야기....~',
    '금씩 알게되는 에스쁘아 🍯템들! ',
    ':이런 작은 차이들이 고퀄을 만드는거죠👏👏👏 게다가 70%할인이라니 아니 살 수가 없어요, 이런 🍯정보 쪼느님이🙏',
    '8만 구독자의 와인디렉터 양갱 이 알려주는 초보자를 위한 세상에서 가장 쉬운 와인 입문서'
]

exports.randomImage = function () {
    return randomImageArr[Math.floor(Math.random() * randomImageArr.length)]
}

exports.randomTitle = function () {
    return randomTitleArr[Math.floor(Math.random() * randomTitleArr.length)]
}

exports.randomMessage = function () {
    return randomMessageArr[Math.floor(Math.random() * randomMessageArr.length)]
}

exports.randomInt = function () {
    return Math.floor(Math.random() * 100)
}

/**
 * Header Cookie 값 파싱 해주는 함수.
 * @param {String} cookie
 * @author hmju
 */
exports.cookieParser = function (cookie = '') {
    return cookie
        .split(';')
        .map(v => v.split('='))
        .map(([k, ...vs]) => [k, vs.join('=')])
        .reduce((acc, [k, v]) => {
            acc[k.trim()] = decodeURIComponent(v);
            return acc;
        }, {});
}

exports.isExpiredToken = function (req) {
    try {
        const token = req.header('Token')
        return token.includes('Expired')
    } catch (err) {
        return false
    }
}
