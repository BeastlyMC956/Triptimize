import './globals.css'
import type { Metadata } from 'next'

export const metadata: Metadata = {
  title: 'Triptimize',
  description: 'Plan Your next trip with us to maximize the enjoyment',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  )
}
