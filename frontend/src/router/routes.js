
const routes = [
  {
    path: '/',
    component: () => import('layouts/Login.vue')
  },
  {
    path: '/main',
    component: () => import('layouts/MyLayout.vue'),
    children: [
<<<<<<< HEAD
      { path: '/main/term/', component: () => import('pages/TermSupervisePage/TermSupervise.vue') },
      { path: '/main/term_group/', component: () => import('pages/TermSupervisePage/TermGroupSupervise.vue') },

      { path: '/main/account/', component: () => import('pages/Accounts/Account.vue') },
      { path: '/main/role/', component: () => import('pages/Accounts/Role.vue') },

      { path: '/main/schedule/', component: () => import('pages/Schedules/Schedule.vue') },
      { path: '/main/car/', component: () => import('pages/Schedules/Car.vue') },

      { path: '/main/article/', component: () => import('pages/Articles/Article.vue') },
      { path: '/main/receipt/', component: () => import('pages/Receipts/Receipt.vue') },

      { path: '/main/term_deadline/', component: () => import('pages/Reports/TermDeadline.vue') },
      { path: '/main/term_sell/', component: () => import('pages/Reports/TermSell.vue') },
      { path: '/main/route_sell/', component: () => import('pages/Reports/RouteSell.vue') },
=======
      { path: '/main/page1/', component: () => import('pages/page1/page1.vue') },
      { path: '/main/page1_pa/', component: () => import('pages/page1/page1_pa.vue') },
      { path: '/main/page1_pb/', component: () => import('pages/page1/page1_pb.vue') },
      { path: '/main/page1_pc/', component: () => import('pages/page1/page1_pc.vue') },
      { path: '/main/page2/', component: () => import('pages/page2.vue') },
      { path: '/main/page3/', component: () => import('pages/page3.vue') },
      { path: '/main/page4/', component: () => import('pages/page4.vue') }
>>>>>>> master
    ]
  }
]

// Always leave this as last one
if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '*',
    component: () => import('pages/Error404.vue')
  })
}

export default routes
