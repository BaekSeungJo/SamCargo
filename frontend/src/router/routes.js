
const routes = [
  {
    path: '/',
    component: () => import('layouts/Login.vue')
  },
  {
    path: '/main',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '/main/page1/', component: () => import('pages/page1/page1.vue') },
      { path: '/main/page1_pa/', component: () => import('pages/page1/page1_pa.vue') },
      { path: '/main/page1_pb/', component: () => import('pages/page1/page1_pb.vue') },
      { path: '/main/page1_pc/', component: () => import('pages/page1/page1_pc.vue') },
      { path: '/main/page2/', component: () => import('pages/page2.vue') },
      { path: '/main/page3/', component: () => import('pages/page3.vue') },
      { path: '/main/page4/', component: () => import('pages/page4.vue') }
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
