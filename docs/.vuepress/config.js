module.exports = {
  title: 'Fanxing Security 1.3.6',
  description: 'Fanxing Security',
  base: '/doc/',
  head: [
    ['link', { rel: 'icon', href: '/favicon.ico' }]
  ],
  themeConfig: {
    logo: '/logo.png',
    repo: 'fxboy/fxboy.github.io/doc',
    docsDir: 'docs',
    editLinks: true,
    editLinkText: '在 Github 上帮助我们编辑此页',
    nav: [
      {text: '指南', link: '/'},
      {text: '开启', link: '/develop/TurnOn'},
      {text: '进阶', link: '/advance/demo'},
    ],
    lastUpdated: 'Last Updated',
    sidebar: [
      {
        title: '开始',
        collapsable: false,
        children: [
          '/start/use', '/start/faq'
        ]
      },
      {
        title: '开发',
        collapsable: false,
        children: [
          '/develop/TurnOn','/develop/anno','/develop/interface', '/develop/Object','/develop/properties','/develop/exception'
        ]
      },
      {
        title: '进阶',
        collapsable: false,
        children: [

        ]
      },
      {
        title: '其它',
        collapsable: false,
        children: [
          '/other/upgrade'
        ]
      }
    ],
    nextLinks: true,
    prevLinks: true,
  },
  plugins: ['@vuepress/back-to-top', require('./plugins/alert')],
  markdown: {
    lineNumbers: true
  }
}
