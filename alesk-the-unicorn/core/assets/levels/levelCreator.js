let blocks = [];


const enemies = [
    {
        x: 400,
        y: 300,
        leftBound: 0,
        rightBound: 500,
    },
    {
        x: 700,
        y: 100,
        leftBound: 500,
        rightBound: 8000,
    }
];

const player = {
    x: 0,
    y: 100,
};

function createBlocks() {
    
    // Create ground
    for (let i = -100; i < 100; i++) {
        blocks.push({
            X: i * 50, Y: 0
        });
    }

    // Create some really large stairs
    for (let i = 0; i < 1000; i++) {
        blocks.push({
            X: 50 * 50 + i * 50, Y: 6 * 50 + 50 * Math.round((i / 6))
        });
    }

    // Create some blocks with hanging spiders
    for (let i = -30; i < 30; i++) {
        blocks.push({
            X: i * 50, 
            Y: 50 * 10,
            hangingEnemy: {
                startY: 50 * 9 + 12,
                range: 200,
                x: i * 50,
                y: 50 * 9 + 12,
            }
        });
    }
}

function createALotOfDisgustingSpiders() {
    for (let i = -300; i < -100; i++) {
        enemies.push({
            x: i * 10,
            y: 100,
            leftBound: -10000,
            rightBound: 10000,
        });
    }
}

createBlocks();
createALotOfDisgustingSpiders();


const level = {
    blocks,
    enemies,
    player,
};

var fs = require('fs');
fs.writeFile('level1.json', JSON.stringify(level), 'utf8', () => {console.log('Done.')});